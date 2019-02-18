package com.squarerepos.squarerepos.di

import com.squarerepos.squarerepos.MainActivity
import dagger.android.AndroidInjector
import dagger.Subcomponent

@Subcomponent
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}