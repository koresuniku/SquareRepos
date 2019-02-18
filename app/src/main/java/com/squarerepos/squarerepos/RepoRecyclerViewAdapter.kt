package com.squarerepos.squarerepos

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squarerepos.squarerepos.database.Repo

class RepoRecyclerViewAdapter(private var repos: List<Repo>) : RecyclerView.Adapter<RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RepoViewHolder {
        return RepoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))
    }

    override fun getItemCount() = repos.count()

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) { holder.bindRepo(repos[position]) }

    fun updateList(repos: List<Repo>) {
        this.repos = repos
        notifyDataSetChanged()
    }
}