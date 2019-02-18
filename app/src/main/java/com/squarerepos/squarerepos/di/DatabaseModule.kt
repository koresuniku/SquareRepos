package com.squarerepos.squarerepos.di

import android.app.Application
import android.arch.persistence.room.Room
import com.squarerepos.squarerepos.database.Database
import com.squarerepos.squarerepos.database.ReposDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): Database {
        return Room.databaseBuilder(application, Database::class.java, "squarerepos.db").build()
    }

    @Provides
    @Singleton
    fun provideReposDao(database: Database): ReposDao = database.reposDao()

}