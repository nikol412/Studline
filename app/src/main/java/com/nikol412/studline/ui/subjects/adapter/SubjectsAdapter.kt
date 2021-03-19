package com.nikol412.studline.ui.subjects.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.studline.api.response.SubjectsResponseItem
import com.nikol412.studline.databinding.ItemSubjectRowBinding
import javax.security.auth.Subject

class SubjectsAdapter(private val listener: OnSubjectClickListener) : RecyclerView.Adapter<SubjectViewHolder>() {

    private var subjectList = mutableListOf<SubjectsResponseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        return SubjectViewHolder(
            ItemSubjectRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.onBind(subjectList[position], listener)
    }

    override fun getItemCount(): Int = subjectList.size

    fun setItems(items: List<SubjectsResponseItem>) {
        if(subjectList.isEmpty()) {
            subjectList = items.toMutableList()
            notifyDataSetChanged()
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean = subjectList[oldItemPosition] == items[newItemPosition]

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    subjectList[oldItemPosition] == items[newItemPosition]

                override fun getNewListSize(): Int = items.size
                override fun getOldListSize(): Int = subjectList.size
            })

            subjectList = items.toMutableList()
            result.dispatchUpdatesTo(this)
        }
    }
}

class SubjectViewHolder(private val binding: ItemSubjectRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(subject: SubjectsResponseItem, listener: OnSubjectClickListener) {
        with(binding) {
            subjectName.text = subject.name
            subjectTeacherName.text = subject.teacherName

            subjectPoints.text = "${subject.completedTasks}/${subject.allTasks}"
            subjectLayout.setOnClickListener {
                listener.onClick(subject.id)
            }

            subjectProgress.progress = subject.statistics ?: 0
        }
    }
}

interface OnSubjectClickListener {
    fun onClick(id: Int)
}

