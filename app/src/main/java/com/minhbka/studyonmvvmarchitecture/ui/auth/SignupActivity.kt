package com.minhbka.studyonmvvmarchitecture.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.minhbka.studyonmvvmarchitecture.R
import com.minhbka.studyonmvvmarchitecture.data.entities.User
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import com.minhbka.studyonmvvmarchitecture.databinding.ActivitySignupBinding
import com.minhbka.studyonmvvmarchitecture.ui.home.HomeActivity
import com.minhbka.studyonmvvmarchitecture.util.hide
import com.minhbka.studyonmvvmarchitecture.util.show
import com.minhbka.studyonmvvmarchitecture.util.snackbar
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity() , AuthListener, KodeinAware{


    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val viewmodel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewmodel

        viewmodel.authListener = this

        viewmodel.getLoggedInUser().observe(this, Observer {user ->

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
