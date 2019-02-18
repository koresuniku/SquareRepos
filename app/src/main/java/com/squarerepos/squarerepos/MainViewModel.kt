package com.squarerepos.squarerepos

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.*
import com.squarerepos.squarerepos.database.Repo
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application,
                                        private val mainRepository: MainRepository) : AndroidViewModel(application) {

    private val _repos: MutableLiveData<List<Repo>> = MutableLiveData()
    val repos: LiveData<List<Repo>>
        get() = _repos

    @SuppressLint("CheckResult")
    fun getRepos() {
        mainRepository.getRepos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _repos.value = it }, { it.printStackTrace(); _repos.value = emptyList() })
    }

    class MainViewModelFactory @Inject constructor(private val application: Application,
                                                   private val mainRepository: MainRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(application, mainRepository) as T
        }
    }
}