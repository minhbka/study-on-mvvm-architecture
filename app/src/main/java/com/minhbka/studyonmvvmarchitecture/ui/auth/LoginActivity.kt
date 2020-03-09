package com.minhbka.studyonmvvmarchitecture.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.minhbka.studyonmvvmarchitecture.R
import com.minhbka.studyonmvvmarchitecture.data.db.AppDatabase
import com.minhbka.studyonmvvmarchitecture.data.entities.User
import com.minhbka.studyonmvvmarchitecture.util.toast
import com.minhbka.studyonmvvmarchitecture.databinding.ActivityLoginBinding
import com.minhbka.studyonmvvmarchitecture.network.MyApi
import com.minhbka.studyonmvvmarchitecture.repository.UserRepository
import com.minhbka.studyonmvvmarchitecture.ui.home.HomeActivity
import com.minhbka.studyonmvvmarchitecture.util.hide
import com.minhbka.studyonmvvmarchitecture.util.show
import com.minhbka.studyonmvvmarchitecture.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = MyApi()
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer {user ->

            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }

        })


    }

    override fun onStarted() {

        progress_bar.show()
    }

    override fun onSuccess(user: User) {

        progress_bar.hide()

    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)

    }
}
