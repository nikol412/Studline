package com.nikol412.studline

import android.app.Application
import com.nikol412.studline.di.dataModule
import com.nikol412.studline.di.networkModule
import com.nikol412.studline.di.repositoryModule
import com.nikol412.studline.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StudlineApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@StudlineApp)
            modules(
                dataModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}