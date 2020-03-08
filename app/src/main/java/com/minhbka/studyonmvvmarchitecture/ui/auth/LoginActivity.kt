package com.minhbka.studyonmvvmarchitecture.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.minhbka.studyonmvvmarchitecture.R
import com.minhbka.studyonmvvmarchitecture.util.toast
import com.minhbka.studyonmvvmarchitecture.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), AuthListenner {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListenner = this
    }

    override fun onStarted() {
        toast("Login Started")
    }

    override fun onSuccess() {
        toast("Login Successes")
    }

    override fun onFailure(message: String) {
        toast("Login Failed")
    }
}
