package com.nikol412.studline.api.repository

import com.nikol412.studline.api.response.SubjectInfoResponse
import com.nikol412.studline.api.response.SubjectsResponse
import io.reactivex.rxjava3.core.Single

interface SubjectsRepository {
    fun getSubjects(): Single<SubjectsResponse>

    fun getSubjectInfo(id: Int): Single<SubjectInfoResponse>
}