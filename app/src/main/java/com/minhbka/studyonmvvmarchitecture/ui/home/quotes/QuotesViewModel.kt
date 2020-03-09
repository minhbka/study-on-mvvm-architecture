package com.minhbka.studyonmvvmarchitecture.ui.home.quotes

import androidx.lifecycle.ViewModel;
import com.minhbka.studyonmvvmarchitecture.repository.QuotesRepository
import com.minhbka.studyonmvvmarchitecture.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {


    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}
