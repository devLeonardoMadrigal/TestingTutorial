package com.example.testingtutorial.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder


/*
* Background - Can only last 5 seconds if no visible component is active, because of security reasons.
               Updated three years ago because of potential mallicious code.
* Foreground
* Bound.
* Playing music we would bind it to the MainActivity
 */

class TodoService : Service() {

    inner class TodoBinder : Binder(){
        fun getService() : TodoService{
            return this@TodoService
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return TodoBinder()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return super.onStartCommand(intent, flags, startId)
    }


}