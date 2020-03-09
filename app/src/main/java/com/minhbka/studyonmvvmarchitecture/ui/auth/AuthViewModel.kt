package com.minhbka.studyonmvvmarchitecture.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.minhbka.studyonmvvmarchitecture.repository.UserRepository
import com.minhbka.studyonmvvmarchitecture.util.Coroutines

// use for both login and signup activity
class AuthViewModel : ViewModel(){

    var email:String? = null
    var password:String? = null
    var authListener:AuthListener? = null
    // important: input view as parameter for onclick function
    fun onLoginButtonClick(view:View){
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            // failed
            authListener?.onFailure("Invalid email or Password")
            return
        }
        // success

        Coroutines.main{
            val response = UserRepository().userLogin(email!!, password!!)
            if (response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            }
            else{
                authListener?.onFailure("Error Code: ${response.code()}")
            }
        }

    }
}