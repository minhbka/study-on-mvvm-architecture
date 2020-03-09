package com.minhbka.studyonmvvmarchitecture.repository


import com.minhbka.studyonmvvmarchitecture.network.MyApi
import com.minhbka.studyonmvvmarchitecture.network.SafeApiRequest
import com.minhbka.studyonmvvmarchitecture.network.responses.AuthResponse

class UserRepository:SafeApiRequest() {

    suspend fun userLogin(email:String, password:String):AuthResponse{
        return apiRequest{MyApi().userLogin(email, password)}

    }
}