package com.bobryshev.genesistest2.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobryshev.genesistest2.databinding.ItemGitRepoBinding
import com.bobryshev.genesistest2.domain.model.ui.GitRepoUi

class GitReposAdapter(private val context: Context, val onClick: (item: GitRepoUi) -> Unit): RecyclerView.Adapter<GitReposAdapter.GitRepoViewHolder>() {

    private val repoList: MutableList<GitRepoUi> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoViewHolder =
        GitRepoViewHolder(ItemGitRepoBinding.inflate(LayoutInflater.from(context)))

    override fun onBindViewHolder(holder: GitRepoViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size

    fun notifyData(list: List<GitRepoUi>) {
        repoList.clear()
        repoList.addAll(list)
        notifyDataSetChanged()
    }

    inner class GitRepoViewHolder(private val view: ItemGitRepoBinding): RecyclerView.ViewHolder(view.root) {

        fun bind(item: GitRepoUi) {
            view.tvName.text = item.name
            view.tvDescription.text = item.description
            view.root.setOnClickListener {
                onClick(item)
            }
        }
    }
}