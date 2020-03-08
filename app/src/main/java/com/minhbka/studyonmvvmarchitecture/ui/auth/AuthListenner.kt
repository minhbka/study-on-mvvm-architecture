package com.minhbka.studyonmvvmarchitecture.ui.auth

interface AuthListenner {

    fun onStarted()
    fun onSuccess()
    fun onFailure(message:String)
}