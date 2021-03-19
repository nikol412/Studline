package com.nikol412.studline.di

import com.nikol412.studline.api.repository.SubjectsRepository
import com.nikol412.studline.api.repository.SubjectsRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<SubjectsRepository> {
        SubjectsRepositoryImpl(get(named("api")))
    }
}