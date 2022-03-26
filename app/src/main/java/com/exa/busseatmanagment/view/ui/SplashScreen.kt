package com.exa.busseatmanagment.view.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exa.busseatmanagment.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

class SplashScreen : AppCompatActivity() {
    var firebaseAuth: FirebaseAuth? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profilel_making)
        supportActionBar?.hide()
        firebaseAuth= FirebaseAuth.getInstance()
        checkLogin()
    }

    private fun checkLogin() {
        if (firebaseAuth?.currentUser !=null){
            startActivity(Intent(this,SelectBusActivity::class.java))
            finish()
        }
        else{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}


