package com.squarerepos.squarerepos.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Repo::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun reposDao(): ReposDao

}