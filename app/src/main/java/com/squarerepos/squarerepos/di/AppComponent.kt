package com.squarerepos.squarerepos.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton
import android.app.Application
import com.squarerepos.squarerepos.SquareRepositoriesApp
import dagger.BindsInstance

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class,
    NetworkModule::class, DatabaseModule::class, ActivityBuilder::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: SquareRepositoriesApp)
}