package com.nikol412.studline.ui.currentSubjectDescription

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SubjectDescriptionViewModel: ViewModel() {
    val taskDescription = MutableLiveData("")

//    init {
//        taskDescription.value = "FragmentContainerView should not be used as a replacement for other ViewGroups (FrameLayout, LinearLayout, etc) outside of Fragment use cases.\n" +
//                "\n" +
//                "FragmentContainerView will only allow views returned by a Fragment's Fragment.onCreateView(LayoutInflater, ViewGroup, Bundle). Attempting to add any other view will result in an IllegalStateException."
//    }
}