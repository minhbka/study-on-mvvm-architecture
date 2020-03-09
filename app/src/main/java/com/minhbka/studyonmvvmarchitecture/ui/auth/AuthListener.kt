package com.minhbka.studyonmvvmarchitecture.ui.auth

import androidx.lifecycle.LiveData
import com.minhbka.studyonmvvmarchitecture.data.entities.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message:String)
}