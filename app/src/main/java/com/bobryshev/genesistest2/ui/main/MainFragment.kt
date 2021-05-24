package com.bobryshev.genesistest2.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bobryshev.genesistest2.R
import com.bobryshev.genesistest2.databinding.FragmentMainBinding
import com.bobryshev.genesistest2.domain.LoadAction
import com.bobryshev.genesistest2.ui.main.adapter.GitReposAdapter
import com.bobryshev.genesistest2.ui.util.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

@AndroidEntryPoint
class MainFragment: Fragment() {

    private lateinit var layout: FragmentMainBinding
    private lateinit var gitReposAdapter: GitReposAdapter

    private var searchText: String? = null

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        layout = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

        viewModel.gitRepoLiveData.observe(viewLifecycleOwner, { gitReposAdapter.notifyData(it) })
        viewModel.errorGitRepoLiveData.observe(viewLifecycleOwner, { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() })

        layout.etSearch.addTextChangedListener { text ->
            searchText = text.toString()
            viewModel.searchGitRepos(text.toString(), LoadAction.INIT)
        }

        layout.btnLastView.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToViewedGitReposFragment())
        }
    }

    private fun setupAdapter() {
        gitReposAdapter = GitReposAdapter(requireContext()) {
            viewModel.saveViewedGitRepo(it)
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it.htmlUrl.orEmpty()))
        }
        layout.rvRepos.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gitReposAdapter
            addOnScrollListener(object : PaginationScrollListener() {
                override fun onReachedTheEnd() {
                    viewModel.searchGitRepos(searchText, LoadAction.NEXT_PAGE)
                }
            })
        }
    }
}