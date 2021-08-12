package com.haydnjm.stocks.android.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.haydnjm.stocks.auth.AuthRepository
import com.haydnjm.stocks.database.DatabaseDriverFactory

class AuthViewModel(context: Context): ViewModel() {

    private var authenticated: Boolean = false
    private var authRepository: AuthRepository = AuthRepository(DatabaseDriverFactory(context))

    fun getAuthenticationStatus(): Boolean {
        Log.i("Session", "GETTING AUTH STATUS")
        authenticated = authRepository.getSession() != null;
        Log.i("Session", authenticated.toString())
        return authenticated
    }
}