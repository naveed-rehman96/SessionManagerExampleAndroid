package com.example.sessionmanagerexampleandroid.utils

import com.example.sessionmanagerexampleandroid.callback.LogoutCallback
import java.util.Timer
import java.util.TimerTask

class SessionManager(private val logoutCallback: LogoutCallback) {

    private val SESSION_TIMEOUT: Long = 5 * 1000 // 10 minutes in milliseconds

    private var sessionTimer: Timer? = null
    private var isUserActive = true

    init {
        startSessionTimer()
    }

    fun startSessionTimer() {
        sessionTimer = Timer(true)

        sessionTimer?.schedule(object : TimerTask() {
            override fun run() {
                if (!isUserActive) {
                    // User is inactive, perform logout or other actions
                    logoutCallback.onLogout()
                } else {
                    // Reset the flag for the next session timeout check
                    isUserActive = false
                }
            }
        }, SESSION_TIMEOUT, SESSION_TIMEOUT)
    }

    fun resetUserActivityTimer() {
        isUserActive = true
    }

    fun stopSessionTimer() {
        sessionTimer?.cancel()
        sessionTimer = null
    }

}