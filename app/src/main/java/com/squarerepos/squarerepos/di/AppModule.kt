package com.squarerepos.squarerepos.di

import dagger.Module
import android.app.Application
import android.content.Context
import javax.inject.Singleton
import dagger.Provides

@Module(subcomponents = [MainActivityComponent::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

}