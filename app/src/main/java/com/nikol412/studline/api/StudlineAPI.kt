package com.nikol412.studline.api

import com.nikol412.studline.api.response.SubjectInfoResponse
import com.nikol412.studline.api.response.SubjectsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StudlineAPI {
    @GET("api/subjects")
    fun getListOfSubjects(): Single<SubjectsResponse>

    @GET("api/info")
    fun getSubjectInfo(@Query("subjectId") subjectId: Int): Single<SubjectInfoResponse>
}