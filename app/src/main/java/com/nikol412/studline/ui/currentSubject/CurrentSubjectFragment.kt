package com.nikol412.studline.ui.currentSubject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nikol412.studline.R
import com.nikol412.studline.commons.Constants.SUBJECT_ID
import com.nikol412.studline.commons.Constants.TASK_DESCRIPTION
import com.nikol412.studline.databinding.FragmentCurrentSubjectBinding
import com.nikol412.studline.ui.currentSubject.adapter.OnTaskClickListener
import com.nikol412.studline.ui.currentSubject.adapter.TasksAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentSubjectFragment : Fragment() {
    private val viewModel by viewModel<CurrentSubjectViewModel>()

    lateinit var binding: FragmentCurrentSubjectBinding

    val adapter by lazy {
        TasksAdapter(object : OnTaskClickListener {
            override fun onClick(id: Int, description: String) {
                findNavController().navigate(
                    R.id.subjectDescriptionFragment, bundleOf(
                        TASK_DESCRIPTION to description
                    )
                )
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_current_subject, container, false)
        binding.vm = viewModel
        binding.recyclerViewTasks.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(SUBJECT_ID)?.let { id ->
            viewModel.fetchTaskInfo(id)
        }

        viewModel.tasksList.observe(viewLifecycleOwner, { list ->
            adapter.setItems(list)
            binding.invalidateAll()
        })

        viewModel.onToastNeeded.observe(viewLifecycleOwner, { string ->
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
        })
    }
}