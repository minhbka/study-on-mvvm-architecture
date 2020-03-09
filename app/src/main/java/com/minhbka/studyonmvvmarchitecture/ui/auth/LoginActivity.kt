package com.minhbka.studyonmvvmarchitecture.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.minhbka.studyonmvvmarchitecture.R
import com.minhbka.studyonmvvmarchitecture.data.entities.User
import com.minhbka.studyonmvvmarchitecture.util.toast
import com.minhbka.studyonmvvmarchitecture.databinding.ActivityLoginBinding
import com.minhbka.studyonmvvmarchitecture.util.hide
import com.minhbka.studyonmvvmarchitecture.util.show
import com.minhbka.studyonmvvmarchitecture.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {

        progress_bar.show()
    }

    override fun onSuccess(user: User) {

        progress_bar.hide()
        root_layout.snackbar("${user.name} is Logged In")

    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)

    }
}
