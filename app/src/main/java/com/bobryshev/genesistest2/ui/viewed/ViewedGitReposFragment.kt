package com.bobryshev.genesistest2.ui.viewed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bobryshev.genesistest2.R
import com.bobryshev.genesistest2.databinding.FragmentViewedGitReposBinding
import com.bobryshev.genesistest2.ui.viewed.adapter.ViewedGitReposAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewedGitReposFragment: Fragment() {

    private lateinit var layout: FragmentViewedGitReposBinding
    private lateinit var mAdapter: ViewedGitReposAdapter

    private val viewModel: ViewedGitReposVIewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layout = DataBindingUtil.inflate(inflater, R.layout.fragment_viewed_git_repos, container, false)
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

        viewModel.viewedGitRepoLiveData.observe(viewLifecycleOwner, { mAdapter.notifyData(it) })
        viewModel.errorLiveData.observe(viewLifecycleOwner, { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() })

        viewModel.getViewedGitRepos()
    }

    private fun setupAdapter() {
        mAdapter = ViewedGitReposAdapter(requireContext())
        layout.rvViewedRepos.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }
}