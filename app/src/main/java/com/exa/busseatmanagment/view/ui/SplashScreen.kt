package com.exa.busseatmanagment.view.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exa.busseatmanagment.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

class SplashScreen : AppCompatActivity() {
    var progressDialog: ProgressDialog? =null
    var firebaseAuth: FirebaseAuth? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        firebaseAuth= FirebaseAuth.getInstance()
        progressDialog?.show()
        checkLogin()
    }

    private fun checkLogin() {
        if (firebaseAuth?.currentUser !=null){
            startActivity(Intent(this,MainActivity::class.java))
            progressDialog?.hide()
            finish()
        }
        else{
            startActivity(Intent(this,LoginActivity::class.java))
            progressDialog?.hide()
            finish()
        }
    }
}