package com.golojodev.stargazer

import android.app.Application
import com.golojodev.stargazer.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class StargazerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(appModules)
            workManagerFactory()
        }
    }
}