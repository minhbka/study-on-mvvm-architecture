package com.minhbka.studyonmvvmarchitecture.repository


import com.minhbka.studyonmvvmarchitecture.data.db.AppDatabase
import com.minhbka.studyonmvvmarchitecture.data.entities.User
import com.minhbka.studyonmvvmarchitecture.network.MyApi
import com.minhbka.studyonmvvmarchitecture.network.SafeApiRequest
import com.minhbka.studyonmvvmarchitecture.network.responses.AuthResponse

class UserRepository(

    private val myApi: MyApi,
    private val db:AppDatabase


) :SafeApiRequest() {


    suspend fun userLogin(email:String, password:String):AuthResponse{
        return apiRequest{myApi.userLogin(email, password)}

    }

    suspend fun userSignup(
        name:String,
        email: String,
        password: String
    ):AuthResponse{
        return apiRequest{
            myApi.userSignup(name, email, password)
        }
    }
    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()
}