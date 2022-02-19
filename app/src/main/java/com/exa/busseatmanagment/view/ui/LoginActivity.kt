package  com.exa.busseatmanagment.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.exa.busseatmanagment.R
//import com.exa.busseatmanagment.R.id.facebookBtn
import com.exa.busseatmanagment.databinding.ActivityLoginBinding
import com.exa.busseatmanagment.utill.CommonListener
import com.exa.busseatmanagment.utill.Utility
import com.exa.busseatmanagment.utill.Utility.makeLog
import com.exa.busseatmanagment.utill.Utility.showToast
import com.exa.busseatmanagment.viewmodel.LoginViewModel
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity(),CommonListener {
    lateinit var viewModel: LoginViewModel
    var auth:FirebaseAuth= FirebaseAuth.getInstance()
    lateinit var progressBar: ProgressBar
    private lateinit var callbackManager:CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()
        viewModel= ViewModelProvider(this)[LoginViewModel::class.java]
        binding.model=viewModel
        viewModel.commonListener=this

    }

//    private fun checkLogin() {
//        if(auth.currentUser!=null){
//            Toast.makeText(this,"Make Login First",Toast.LENGTH_LONG)
//        }else{
//            updateUI(null)
//        }
//    }

//    override fun onClick(v: View?) {
//        when(v?.id){
//            facebookBtn->{
//                if(viewModelProvider.login("sbiitju@gmail.com","12345678")){
//                    Log.d("Shahin Bashar","Success")
//                }else{
//                    Log.d("Failed","Login Failed")
//                }
//            }
//            R.id.login_button->{
//
//            }
//            else->{
//
//            }
//
//        }
//    }
//    private fun handleFacebookAccessToken(token: AccessToken) {
//        Log.d("TOken", "handleFacebookAccessToken:$token")
//
//        val credential = FacebookAuthProvider.getCredential(token.token)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("Sign in success", "signInWithCredential:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w("Failure", "signInWithCredential:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
//                }
//            }
//    }

//    private fun updateUI(user: FirebaseUser?) {
//        if(user!=null){
//            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
//            finish()
//        }else{
//            Toast.makeText(this,"Failed",Toast.LENGTH_LONG)
//        }
//
//    }


    override fun onSuccess(msg: String) {
        this.showToast("Success")
        Log.d("Check",msg)
    }

    override fun onFailed(msg: String) {
        this.showToast("Failed")
        Log.d("Check",msg)
    }

    override fun onNavigate() {
        startActivity(Intent(this,SelectBusActivity::class.java))
        finish()
    }
}