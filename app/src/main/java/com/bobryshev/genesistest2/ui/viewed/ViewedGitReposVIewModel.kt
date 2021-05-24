package com.bobryshev.genesistest2.ui.viewed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bobryshev.genesistest2.domain.model.ui.GitRepoUi
import com.bobryshev.genesistest2.domain.usecase.IViewedGitRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ViewedGitReposVIewModel @Inject constructor(private val viewedGitRepoUseCase: IViewedGitRepoUseCase): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _viewedGitRepoLiveData: MutableLiveData<List<GitRepoUi>> = MutableLiveData()
    val viewedGitRepoLiveData: LiveData<List<GitRepoUi>>
        get() = _viewedGitRepoLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String>
        get()= _errorLiveData

    fun getViewedGitRepos() {
        viewedGitRepoUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                _viewedGitRepoLiveData.postValue(it)
            }, onError = {
                _errorLiveData.postValue(it.message)
            }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}