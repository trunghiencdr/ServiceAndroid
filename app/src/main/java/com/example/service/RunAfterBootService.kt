package com.example.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast


class RunAfterBootService: Service() {
    private val TAG_BOOT_EXECUTE_SERVICE = "BOOT_BROADCAST_SERVICE"
    fun RunAfterBootService() {}
    override fun onBind(intent: Intent?): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG_BOOT_EXECUTE_SERVICE, "RunAfterBootService onCreate() method.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val message = "RunAfterBootService onStartCommand() method."
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
        Log.d(TAG_BOOT_EXECUTE_SERVICE, "RunAfterBootService onStartCommand() method.")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}