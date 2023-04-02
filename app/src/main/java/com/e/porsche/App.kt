package com.e.porsche

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.e.porsche.managers.Injector

class App : Application() {
    companion object {
        @JvmStatic
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Injector.application = this
        Injector.initData()

//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
