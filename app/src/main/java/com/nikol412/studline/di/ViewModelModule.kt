package com.nikol412.studline.di

import com.nikol412.studline.ui.currentSubject.CurrentSubjectViewModel
import com.nikol412.studline.ui.currentSubjectDescription.SubjectDescriptionViewModel
import com.nikol412.studline.ui.subjects.SubjectsViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val viewModelModule = module {
    viewModel { SubjectsViewModel(get()) }
    viewModel { CurrentSubjectViewModel(get()) }
    viewModel { SubjectDescriptionViewModel() }
}