package com.minhbka.studyonmvvmarchitecture.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.minhbka.studyonmvvmarchitecture.repository.UserRepository
import com.minhbka.studyonmvvmarchitecture.util.ApiException
import com.minhbka.studyonmvvmarchitecture.util.Coroutines
import com.minhbka.studyonmvvmarchitecture.util.NoInternetException

// use for both login and signup activity
class AuthViewModel(
    private val repository: UserRepository
) : ViewModel(){

    var email:String? = null
    var password:String? = null
    var authListener:AuthListener? = null


    fun getLoggedInUser() = repository.getUser()
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
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }
            catch (e:ApiException){
                authListener?.onFailure(e.message!!)
            }
            catch (e:NoInternetException){
                authListener?.onFailure(e.message!!)
            }


        }

    }
}