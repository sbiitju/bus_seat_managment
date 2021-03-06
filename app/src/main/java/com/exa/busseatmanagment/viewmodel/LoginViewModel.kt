package com.exa.busseatmanagment.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exa.busseatmanagment.utill.CommonListener
import com.exa.busseatmanagment.utill.Utility
import com.exa.busseatmanagment.view.ui.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
    var email= MutableLiveData<String>()
    var password= MutableLiveData<String>()
    var commonListener: CommonListener?=null
    fun onEmailChange(char: CharSequence,start:Int,end:Int,count:Int){
        email.postValue(char.toString())
    }
    fun onPasswordChange(char: CharSequence,start:Int,end:Int,count:Int){
        password.postValue(char.toString())
    }
    var firebaseAuth:FirebaseAuth= FirebaseAuth.getInstance()
    fun makeLogin(view: View){
        if (email.value.isNullOrEmpty()||password.value.isNullOrEmpty()){
            commonListener?.onFailed("Failed")
            return
        }else{
            firebaseAuth.signInWithEmailAndPassword(email.value.toString(),password.value.toString()).addOnCompleteListener(
                OnCompleteListener {
                    if (it.isSuccessful) commonListener?.onSuccess("Success")
                    else commonListener?.onFailed("Failed")
                })
        }

    }
    fun signUp(view: View){
        Log.d("check",email.value.toString())
        if (email.value.isNullOrEmpty()||password.value.isNullOrEmpty()){
            commonListener?.onFailed("Failed")
            return
        }else{
            firebaseAuth.createUserWithEmailAndPassword(email.value.toString(),password.value.toString()).addOnCompleteListener(
                OnCompleteListener {
                    if (it.isSuccessful) commonListener?.onSuccess("Success")
                    else commonListener?.onFailed("Failed")
                })
        }
    }
}