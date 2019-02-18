package com.squarerepos.squarerepos

import com.squarerepos.squarerepos.database.Repo
import io.reactivex.Observable
import retrofit2.http.GET

interface SquareReposApiService {

    @GET("/users/square/repos")
    fun fetchRepos(): Observable<List<Repo>>
}