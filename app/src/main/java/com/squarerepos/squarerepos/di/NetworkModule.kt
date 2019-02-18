package com.squarerepos.squarerepos.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squarerepos.squarerepos.SquareReposApiService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(subcomponents = [MainActivityComponent::class])
class NetworkModule {

    val BASE_URL: String = "api.github.com"

    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache =
        Cache(application.cacheDir, 10 * 1024 * 1024 /* 10 MiB */)

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient =
        OkHttpClient.Builder().apply { cache(cache) }.build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HttpUrl.Builder().host(BASE_URL).scheme("https").build())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSquareReposApiService(retrofit: Retrofit): SquareReposApiService =
        retrofit.create(SquareReposApiService::class.java)

}