package com.bobryshev.genesistest2.ui.viewed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bobryshev.genesistest2.R
import com.bobryshev.genesistest2.databinding.ItemGitRepoBinding
import com.bobryshev.genesistest2.domain.model.ui.GitRepoUi

class ViewedGitReposAdapter(private val context: Context): RecyclerView.Adapter<ViewedGitReposAdapter.ViewedGitReposViewHolder>() {

    private val repoList: MutableList<GitRepoUi> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewedGitReposViewHolder =
        ViewedGitReposViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_git_repo, parent, false))

    override fun onBindViewHolder(holder: ViewedGitReposViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size

    fun notifyData(list: List<GitRepoUi>) {
        repoList.clear()
        repoList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewedGitReposViewHolder(private val view: ItemGitRepoBinding): RecyclerView.ViewHolder(view.root) {

        fun bind(item: GitRepoUi) {

        }
    }
}