package com.example.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BootDeviceReceiver: BroadcastReceiver() {
    companion object{
            private val TAG_BOOT_BROADCAST_RECEIVER = "BOOT_BROADCAST_RECEIVER"
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        val message = "BootDeviceReceiver onReceiver action is $action"
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        Log.d(TAG_BOOT_BROADCAST_RECEIVER, action.toString())
        if(Intent.ACTION_BOOT_COMPLETED.equals(action)){
            //start service in background

            //startServiceDirectly(context)
            startServiceByAlarm(context)
        }
    }

    private fun startServiceDirectly(context: Context?) {
        try {
            while (true){
                val message = "BootDeviceReceiver onReceive start service directly."
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                Log.d(TAG_BOOT_BROADCAST_RECEIVER, message)
                // This intent is used to start background service. The same service will be invoked for each invoke in the loop.

                val intent = Intent(context, RunAfterBootService::class.java)
                context?.startService(intent)
                // current will sleep
                Thread.sleep(5000)
            }
        }catch (e: Exception){
            Log.d(TAG_BOOT_BROADCAST_RECEIVER, "exception startServiceByAlarm ${e.message}")
        }
    }
    /* Create an repeat Alarm that will invoke the background service for each execution time.
   * The interval time can be specified by your self.  */


    private fun startServiceByAlarm(context: Context?){
        // get alarm manager
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // create intent to invoke the background service

        val intent = Intent(context, RunAfterBootService::class.java)
        val pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val startTime = System.currentTimeMillis()
        val intervalTime = 30*1000L
        val message = "Start service use repeat alarm"
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        Log.d(TAG_BOOT_BROADCAST_RECEIVER, message);
        // Create repeat alarm.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startTime, intervalTime, pendingIntent);

    }
}