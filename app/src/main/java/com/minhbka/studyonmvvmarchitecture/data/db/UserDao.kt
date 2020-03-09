package com.minhbka.studyonmvvmarchitecture.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minhbka.studyonmvvmarchitecture.data.entities.CURRENT_USER_ID
import com.minhbka.studyonmvvmarchitecture.data.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    @Query("Select *from user where uid = $CURRENT_USER_ID")
    fun getuser():LiveData<User>

}