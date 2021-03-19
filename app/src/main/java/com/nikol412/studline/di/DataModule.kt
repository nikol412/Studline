package com.nikol412.studline.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module

val dataModule = module {
    single<Gson> {
        GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .excludeFieldsWithoutExposeAnnotation()
            .enableComplexMapKeySerialization()
            .create()
    }
}