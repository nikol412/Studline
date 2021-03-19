package com.nikol412.studline.ui.subjects

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
import com.nikol412.studline.databinding.FragmentSubjectsBinding
import com.nikol412.studline.ui.subjects.adapter.OnSubjectClickListener
import com.nikol412.studline.ui.subjects.adapter.SubjectsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class SubjectsFragment : Fragment() {

    private val viewModel by viewModel<SubjectsViewModel>()

    lateinit var binding: FragmentSubjectsBinding

    val adapter by lazy {
        SubjectsAdapter(object : OnSubjectClickListener {
            override fun onClick(id: Int) {
                findNavController().navigate(
                    R.id.currentSubjectFragment,
                    bundleOf(SUBJECT_ID to id)
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_subjects, container, false)
        binding.vm = viewModel
        binding.recyclerViewSubjects.adapter = adapter

        viewModel.subjects.observe(viewLifecycleOwner, { subjects ->
            adapter.setItems(subjects)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onToastNeeded.observe(viewLifecycleOwner, { string ->
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
        })
    }
}