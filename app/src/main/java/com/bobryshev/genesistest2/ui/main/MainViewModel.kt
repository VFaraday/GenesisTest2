package com.bobryshev.genesistest2.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bobryshev.genesistest2.domain.LoadAction
import com.bobryshev.genesistest2.domain.model.ui.GitRepoUi
import com.bobryshev.genesistest2.domain.usecase.ISaveViewedGitRepoUseCase
import com.bobryshev.genesistest2.domain.usecase.ISearchGitReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val searchGitReposUseCase: ISearchGitReposUseCase,
                                        private val saveViewedGitRepoUseCase: ISaveViewedGitRepoUseCase): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _gitRepoLiveData: MutableLiveData<List<GitRepoUi>> = MutableLiveData()
    val gitRepoLiveData: LiveData<List<GitRepoUi>>
        get() = _gitRepoLiveData

    private val _errorGitRepoLiveData: MutableLiveData<String> = MutableLiveData()
    val errorGitRepoLiveData: LiveData<String>
        get() = _errorGitRepoLiveData

    fun searchGitRepos(searchText: String?, loadAction: LoadAction) {
        searchGitReposUseCase.invoke(searchText, loadAction)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                _gitRepoLiveData.postValue(it)
            }, onError = {
                _errorGitRepoLiveData.postValue(it.message)
            }).addTo(compositeDisposable)
    }

    fun saveViewedGitRepo(gitRepoUi: GitRepoUi) {
        saveViewedGitRepoUseCase.invoke(gitRepoUi)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {  }
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}