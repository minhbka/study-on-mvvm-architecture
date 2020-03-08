package com.minhbka.studyonmvvmarchitecture.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.minhbka.studyonmvvmarchitecture.repository.UserRepository

// use for both login and signup activity
class AuthViewModel : ViewModel(){

    var email:String? = null
    var password:String? = null
    var authListenner:AuthListenner? = null
    // important: input view as parameter for onclick function
    fun onLoginButtonClick(view:View){
        authListenner?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            // failed
            authListenner?.onFailure("Invalid email or Password")
            return
        }
        // success
        val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListenner?.onSuccess(loginResponse)
    }
}