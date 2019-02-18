package com.squarerepos.squarerepos

import android.support.v7.widget.RecyclerView
import android.view.View
import com.squarerepos.squarerepos.database.Repo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo.*
import android.content.Intent
import android.net.Uri

class RepoViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindRepo(repo: Repo) {
        name.text = repo.name
        itemView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.htmlUrl))
            itemView.context.startActivity(browserIntent)
        }
    }
}