package com.squarerepos.squarerepos.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repos")
data class Repo(@PrimaryKey(autoGenerate = true) var id: Int?,
                @ColumnInfo(name = "name") val name: String,
                @ColumnInfo(name = "url")
                @SerializedName("html_url") val htmlUrl: String)