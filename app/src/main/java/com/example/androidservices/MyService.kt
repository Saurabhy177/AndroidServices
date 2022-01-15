package com.example.androidservices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService: Service() {

    private val TAG = "MyService"

    init {
        Log.d(TAG, "Service is started ...")
    }

    /**
     * onStartCommand() method happens in the main thread. So, we prefer to create a new thread
     * for performing time-taking tasks.
     * Its return value determines how the android system will treat them once a service
     * was killed due to limitation of background resources:
     *
     * a) START_NOT_STICKY: Service won't be recreated.
     * b) START_STICKY: Service will be recreated if possible and
     * the last intent will not be redelivered on to onStartCommand() & will be null.
     * c) START_REDELIVER_INTENT: Service will be recreated if possible and
     * the last intent will be redelivered on to onStartCommand().
     * */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val dataString = intent?.getStringExtra("EXTRA_DATA")
        dataString?.let {
            Log.d(TAG, dataString)
        }
        return START_STICKY
    }

    /**
     * This method is used to connect multiple clients to the service at the same time.
     * Since we don't need it here, we can return null.
     * */
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service is being killed ...")
    }
}