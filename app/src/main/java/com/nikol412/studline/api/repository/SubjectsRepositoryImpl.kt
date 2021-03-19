package com.nikol412.studline.api.repository

import com.nikol412.studline.api.StudlineAPI
import com.nikol412.studline.api.response.SubjectInfoResponse
import com.nikol412.studline.api.response.SubjectsResponse
import io.reactivex.rxjava3.core.Single

class SubjectsRepositoryImpl(private val studlineAPI: StudlineAPI): SubjectsRepository {

    override fun getSubjectInfo(id: Int): Single<SubjectInfoResponse> {
        return studlineAPI.getSubjectInfo(id)
    }

    override fun getSubjects(): Single<SubjectsResponse> {
        return  studlineAPI.getListOfSubjects()
    }

}