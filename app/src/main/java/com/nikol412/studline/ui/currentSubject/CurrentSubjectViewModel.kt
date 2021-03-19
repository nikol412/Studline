package com.nikol412.studline.ui.currentSubject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikol412.studline.api.repository.SubjectsRepository
import com.nikol412.studline.api.response.Task
import com.nikol412.studline.commons.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable

class CurrentSubjectViewModel(private val repository: SubjectsRepository) : ViewModel() {

    val teacherName = MutableLiveData("")
    val teacherEmail = MutableLiveData("")
    val points = MutableLiveData("")

    val tasksList = MutableLiveData<List<Task>>()
    private val compositeDisposable = CompositeDisposable()

    val onToastNeeded = SingleLiveEvent<String>()
    val onUpdateUI = SingleLiveEvent<Any>()

    fun fetchTaskInfo(id: Int) {
        compositeDisposable.add(
            repository.getSubjectInfo(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ subjectResponse ->
                    teacherName.postValue(subjectResponse.teacherName)
                    teacherEmail.postValue(subjectResponse.email)
                    points.postValue("${subjectResponse.completedTasks}/${subjectResponse.allTasks}")

                    tasksList.postValue(subjectResponse.tasks)
                }, {
                    onToastNeeded.postValue("Some error, please try again :(")
                })
        )
    }
}