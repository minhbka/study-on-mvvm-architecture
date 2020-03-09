package com.minhbka.studyonmvvmarchitecture.network.responses

import com.minhbka.studyonmvvmarchitecture.data.entities.Quote

data class QuotesResponse (
    val isSuccessful : Boolean,
    val quotes: List<Quote>
)