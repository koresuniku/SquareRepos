package com.squarerepos.squarerepos

import android.annotation.SuppressLint
import com.squarerepos.squarerepos.database.Repo
import com.squarerepos.squarerepos.database.ReposDao
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: SquareReposApiService,
                                         private val reposDao: ReposDao) {

    fun getRepos(): Single<List<Repo>> = Single.create { emitter ->
        fetchFromDb(emitter, this::fetchFromApi)
    }

    @SuppressLint("CheckResult")
    private fun fetchFromDb(emitter: SingleEmitter<List<Repo>>, onNothing: (SingleEmitter<List<Repo>>) -> (Unit)) {
        reposDao.getRepos()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { repos -> if (repos.isNotEmpty()) emitter.onSuccess(repos) else onNothing(emitter) },
                { it.printStackTrace(); emitter.onSuccess(emptyList()) },
                { onNothing(emitter) })
    }

    @SuppressLint("CheckResult")
    private fun fetchFromApi(emitter: SingleEmitter<List<Repo>>) {
        apiService.fetchRepos()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .onErrorReturn { emptyList() }
            .subscribe { reposDao.storeRepos(it); emitter.onSuccess(it) }
    }
}
