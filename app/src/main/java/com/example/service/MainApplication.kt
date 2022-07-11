package com.example.service

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Bootstrap.setContext(this)
    }
}

class Bootstrap private constructor() {

    companion object {
        @Volatile
        private var instance: Bootstrap? = null
        private var applicationContext: Application? = null

        fun getInstance(): Bootstrap? {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = Bootstrap()
                    }
                }
            }
            return instance
        }

        fun setContext(application: Application) {
            this.applicationContext = application
        }
    }

    val sharePre1 = SharedPreferences(applicationContext)
    val checkNetwork = CheckNetwork(applicationContext)
}

