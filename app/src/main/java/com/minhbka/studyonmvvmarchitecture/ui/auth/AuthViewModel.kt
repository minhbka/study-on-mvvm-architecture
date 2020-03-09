package com.minhbka.studyonmvvmarchitecture.ui.auth

import android.content.Intent
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

    var name:String? = null
    var email:String? = null
    var password:String? = null
    var authListener:AuthListener? = null
    var passwordconfirm:String? = null


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

    fun onSignUp(view: View){
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClick(view:View){
        authListener?.onStarted()

        if (name.isNullOrEmpty()){
            authListener?.onFailure("Name is required")
            return
        }
        if (email.isNullOrEmpty()){
            authListener?.onFailure("Email is required")
            return
        }
        if (password.isNullOrEmpty()){
            authListener?.onFailure("Password is required")
            return
        }
        if (password != passwordconfirm){
            authListener?.onFailure("Password did not match")
            return

        }

        // success

        Coroutines.main{
            try {
                val authResponse = repository.userSignup(name!!,email!!, password!!)
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