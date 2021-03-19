package com.nikol412.studline.ui.currentSubjectDescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nikol412.studline.R
import com.nikol412.studline.commons.Constants.TASK_DESCRIPTION
import com.nikol412.studline.databinding.FragmentSubjectDescriptionBinding
import com.nikol412.studline.databinding.FragmentSubjectsBinding
import com.nikol412.studline.ui.subjects.SubjectsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubjectDescriptionFragment: Fragment() {
    private val viewModel by viewModel<SubjectDescriptionViewModel>()

    lateinit var binding: FragmentSubjectDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_subject_description, container, false)
        binding.vm = viewModel

        arguments?.getString(TASK_DESCRIPTION)?.let { description ->
            viewModel.taskDescription.value = description
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}