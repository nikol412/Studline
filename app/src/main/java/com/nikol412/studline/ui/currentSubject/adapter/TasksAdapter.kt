package com.nikol412.studline.ui.currentSubject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.studline.R
import com.nikol412.studline.api.response.Task
import com.nikol412.studline.databinding.ItemTaskRowBinding

class TasksAdapter(private val listener: OnTaskClickListener) :
    RecyclerView.Adapter<TaskViewHolder>() {
    private var tasksList = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(tasksList[position], listener)
    }

    override fun getItemCount(): Int = tasksList.size

    fun setItems(items: List<Task>) {
        if (tasksList.isEmpty()) {
            tasksList = items.toMutableList()
            notifyDataSetChanged()
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean = tasksList[oldItemPosition] == items[newItemPosition]

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    tasksList[oldItemPosition] == items[newItemPosition]

                override fun getNewListSize(): Int = items.size
                override fun getOldListSize(): Int = tasksList.size
            })

            tasksList = items.toMutableList()
            result.dispatchUpdatesTo(this)
        }
    }
}

class TaskViewHolder(private val binding: ItemTaskRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(task: Task, listener: OnTaskClickListener) {
        with(binding) {
            textViewTaskName.text = task.name
            textViewDeadline.text = task.deadline
            if (task.isCompleted == true) {
                imageViewIsCompleted.setImageResource(R.drawable.ic_task_completed)
            } else {
                imageViewIsCompleted.setImageResource(R.drawable.ic_task_uncompleted)
            }

            taskLayout.setOnClickListener {
                task.description?.let {
                    listener.onClick(task.id, it)
                }
            }
        }
    }
}

interface OnTaskClickListener {
    fun onClick(id: Int, taskDescription: String)
}