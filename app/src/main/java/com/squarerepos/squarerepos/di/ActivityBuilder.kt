package com.squarerepos.squarerepos.di

import android.app.Activity
import dagger.android.AndroidInjector
import com.squarerepos.squarerepos.MainActivity
import dagger.android.ActivityKey
import dagger.multibindings.IntoMap
import dagger.Binds
import dagger.Module

@Module
abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivity(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>

}