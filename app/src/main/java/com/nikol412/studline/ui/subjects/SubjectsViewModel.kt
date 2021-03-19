package com.nikol412.studline.ui.subjects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikol412.studline.api.StudlineAPI
import com.nikol412.studline.api.repository.SubjectsRepository
import com.nikol412.studline.api.response.SubjectsResponseItem
import com.nikol412.studline.commons.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SubjectsViewModel(private val repository: SubjectsRepository) : ViewModel() {
    val name = "kekekekekekekek"
    private val compositeDisposable = CompositeDisposable()

    val subjects = MutableLiveData<List<SubjectsResponseItem>>()

    val onToastNeeded = SingleLiveEvent<String>()

    init {
        fetchSubjects()
    }


    fun fetchSubjects() {
        compositeDisposable.add(
            repository.getSubjects()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ subjectsResponse ->
                    subjects.postValue(subjectsResponse)
                }, {
                    onToastNeeded.postValue("Some error, please try again :(")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}