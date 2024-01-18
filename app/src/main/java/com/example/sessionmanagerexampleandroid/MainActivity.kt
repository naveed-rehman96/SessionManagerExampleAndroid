package com.example.sessionmanagerexampleandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import com.example.sessionmanagerexampleandroid.callback.LogoutCallback
import com.example.sessionmanagerexampleandroid.utils.SessionManager

class MainActivity : AppCompatActivity() , LogoutCallback {
    
    var sessionManager : SessionManager ?=null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sessionManager = SessionManager(this)

    }
    override fun onUserInteraction() {
        super.onUserInteraction()
        sessionManager?.resetUserActivityTimer()
    }

    override fun onLogout() {

        runOnUiThread {
            Toast.makeText(this, "LogOut Call", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        // Stop the session timer when the activity is destroyed
        sessionManager?.stopSessionTimer()
    }
}