package com.nikol412.studline.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SubjectsResponse: ArrayList<SubjectsResponseItem>()

data class SubjectsResponseItem(
    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("staticstics")
    val statistics: Int? = null,

    @Expose
    @SerializedName("teacherName")
    val teacherName: String? = null,

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("completedTasks")
    val completedTasks: Int? = null,

    @Expose
    @SerializedName("allTasks")
    val allTasks: Int? = null
)
