package com.exa.busseatmanagment.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.exa.busseatmanagment.utill.Utility
import com.exa.busseatmanagment.view.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
    var firebaseAuth:FirebaseAuth= FirebaseAuth.getInstance()
    fun login(email:String,password:String):Boolean{
        var boolean=false
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            boolean=true
        }.addOnFailureListener {
            boolean=false
        }
        return boolean
    }
}