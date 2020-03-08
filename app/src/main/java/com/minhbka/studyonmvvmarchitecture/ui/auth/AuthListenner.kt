package com.minhbka.studyonmvvmarchitecture.ui.auth

import androidx.lifecycle.LiveData

interface AuthListenner {

    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message:String)
}