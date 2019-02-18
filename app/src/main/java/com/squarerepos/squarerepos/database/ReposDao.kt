package com.squarerepos.squarerepos.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Maybe

@Dao
interface ReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeRepos(repos: List<Repo>)

    @Query("SELECT * FROM ${"repos"}")
    fun getRepos(): Maybe<List<Repo>>
}