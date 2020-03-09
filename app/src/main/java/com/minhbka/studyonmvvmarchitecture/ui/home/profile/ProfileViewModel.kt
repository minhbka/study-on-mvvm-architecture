package com.minhbka.studyonmvvmarchitecture.ui.home.profile

import androidx.lifecycle.ViewModel
import com.minhbka.studyonmvvmarchitecture.repository.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {
    // when user data is changed it will automatically reflect to view
    val user = repository.getUser()
}
