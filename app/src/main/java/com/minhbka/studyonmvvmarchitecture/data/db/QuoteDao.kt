package com.minhbka.studyonmvvmarchitecture.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minhbka.studyonmvvmarchitecture.data.entities.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllQuotes(quotes: List<Quote>)

    @Query("Select * from Quote")
    fun getQuotes() : LiveData<List<Quote>>

}