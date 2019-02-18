package com.squarerepos.squarerepos.di

import android.app.Application
import android.arch.lifecycle.ViewModelProviders
import com.squarerepos.squarerepos.MainRepository
import com.squarerepos.squarerepos.MainViewModel
import com.squarerepos.squarerepos.SquareReposApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.squarerepos.squarerepos.MainActivity
import com.squarerepos.squarerepos.database.ReposDao

@Module
class MainActivityModule {

    @Provides
    fun provideMainView(mainActivity: MainActivity): MainActivity = mainActivity

    @Provides
    @Singleton
    fun provideMainRepository(squareReposApiService: SquareReposApiService, reposDao: ReposDao) = MainRepository(squareReposApiService, reposDao)

    @Provides
    @Singleton
    fun provideMainViewModelFactory(application: Application, mainRepository: MainRepository) =
        MainViewModel.MainViewModelFactory(application, mainRepository)

    @Provides
    @Singleton
    fun provideMainViewModel(mainActivity: MainActivity, mainViewModelFactory: MainViewModel.MainViewModelFactory): MainViewModel {
        return ViewModelProviders.of(mainActivity, mainViewModelFactory)[MainViewModel::class.java]
    }
}