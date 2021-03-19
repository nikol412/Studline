package com.nikol412.studline.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubjectInfoResponse(
    @Expose
    @SerializedName("teacher")
    val teacherName: String? = null,

    @Expose
    @SerializedName("tasks_completed")
    val completedTasks: Int? = null,

    @Expose
    @SerializedName("tasks_all")
    val allTasks: Int? = null,

    @Expose
    @SerializedName("teacherEmail")
    val email: String? = null,

    @Expose
    @SerializedName("tasks")
    val tasks: List<Task>? = null
)

data class Task(
    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("description")
    var description: String? = null,

    @Expose
    @SerializedName("isCompleted")
    val isCompleted: Boolean? = null,

    @Expose
    @SerializedName("deadline")
    val deadline: String? = null
)
