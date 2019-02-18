package com.squarerepos.squarerepos

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.squarerepos.squarerepos.database.Repo
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var mainViewModel: MainViewModel
    private lateinit var _recyclerViewAdapter: RepoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation).apply {
            ContextCompat.getDrawable(this@MainActivity, R.drawable.line_divider)?.let { setDrawable(it) }
        })

        mainViewModel.repos.observe(this, Observer { repos -> repos?.let { displayRepos(repos) } })
        mainViewModel.getRepos()
    }

    private fun displayRepos(repos: List<Repo>) {
        Log.d(MainActivity::class.java.simpleName, "repos count: ${repos.count()}")
        progressBar.visibility = View.GONE
        _recyclerViewAdapter = RepoRecyclerViewAdapter(repos)
        recyclerView.adapter = _recyclerViewAdapter
    }
}
