package com.valentin.secondhomework.application

import android.app.Application
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            modules(listOf(viewModelsModules, serviceModules))
        }
    }
}