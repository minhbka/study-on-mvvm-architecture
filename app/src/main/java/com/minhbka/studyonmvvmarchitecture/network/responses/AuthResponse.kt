package com.minhbka.studyonmvvmarchitecture.network.responses

import com.minhbka.studyonmvvmarchitecture.data.entities.User

data class AuthResponse (
    val isSuccessful : Boolean?,
    val message: String?,
    val user : User?

    )