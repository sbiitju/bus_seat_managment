package  com.exa.busseatmanagment.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.exa.busseatmanagment.R
//import com.exa.busseatmanagment.R.id.facebookBtn
import com.exa.busseatmanagment.databinding.ActivityLoginBinding
import com.exa.busseatmanagment.model.StudentModel
import com.exa.busseatmanagment.model.TeacherModel
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
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity(),CommonListener {
    lateinit var viewModel: LoginViewModel
    var auth:FirebaseAuth= FirebaseAuth.getInstance()
    lateinit var progressBar: ProgressBar
    var binding: ActivityLoginBinding? =null
    private lateinit var callbackManager:CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        supportActionBar?.hide()
        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()
        viewModel= ViewModelProvider(this)[LoginViewModel::class.java]
        binding!!.model=viewModel
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
        if(msg.contains("dialog")){
            var alertDialog=AlertDialog.Builder(this)
            var view=LayoutInflater.from(this).inflate(R.layout.check,null)
            var studentBtn=view.findViewById<Button>(R.id.student)
            var teacherBtn=view.findViewById<Button>(R.id.teacher)
            var stuffBtn=view.findViewById<Button>(R.id.stuff)
            studentBtn.setOnClickListener {
                var departmentName=resources.getStringArray(R.array.departmentName)
                var adapter=ArrayAdapter(this@LoginActivity,android.R.layout.simple_expandable_list_item_1,departmentName)
                var studentDialog=AlertDialog.Builder(this)
                var view=LayoutInflater.from(this).inflate(R.layout.profilel_making,null)
                var departementET=view.findViewById<AutoCompleteTextView>(R.id.sDepartment)
                var name=view.findViewById<EditText>(R.id.sName)
                var number=view.findViewById<EditText>(R.id.sMobileNumber)
                var regNumber=view.findViewById<EditText>(R.id.sRegNumber)
                var email=view.findViewById<EditText>(R.id.sEmail)
                var password=view.findViewById<EditText>(R.id.sPassword)
                var classRoll=view.findViewById<EditText>(R.id.sClassRoll)
                var examRoll=view.findViewById<EditText>(R.id.sExamRoll)
                var sName=name.text.toString()
                var sNumber=number.text.toString()
                var sEmail=email.text.toString()
                var sRegNumber=regNumber.text.toString()
                var sPassword=password.text.toString()
                var sClassroll=classRoll.text.toString()
                var sExamRoll=examRoll.text.toString()
                var sDepartment=departementET.text.toString()
                var studentModel=StudentModel(sName,sNumber,sDepartment,sEmail,sRegNumber,sClassroll,sExamRoll)
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(sEmail,sPassword).addOnCompleteListener {
                    if(it.isSuccessful){
                        var student=TeacherModel(sName,sEmail,sNumber,sDepartment)
                        var studentModel=StudentModel(sName,sNumber,sDepartment,sEmail,sRegNumber,sClassroll,sExamRoll)
                        FirebaseDatabase.getInstance().getReference(sNumber).setValue(studentModel)
                        var reference=
                            auth.currentUser?.let { it1 ->
                                FirebaseDatabase.getInstance().getReference(
                                    it1.uid)
                            }
                        reference?.setValue(student)?.addOnCompleteListener {
                            if(it.isSuccessful){
                                startActivity(Intent(this@LoginActivity,SelectBusActivity::class.java))
                                finish()
                            }
                            else{
                                Toast.makeText(this@LoginActivity,"Failed",Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                departementET.setAdapter(adapter)
                studentDialog.setView(view).setCancelable(true).show()
            }
            teacherBtn.setOnClickListener {
                var departmentName=resources.getStringArray(R.array.departmentName)
                var adapter=ArrayAdapter(this@LoginActivity,android.R.layout.simple_expandable_list_item_1,departmentName)
                var teacherDialog=AlertDialog.Builder(this)
                var view=LayoutInflater.from(this).inflate(R.layout.tcrprofilemaking,null)
                var tName=view.findViewById<EditText>(R.id.tName)
                var tDepartment=view.findViewById<AutoCompleteTextView>(R.id.tDepartment)
                tDepartment.setAdapter(adapter)
                var tMobileNumber=view.findViewById<EditText>(R.id.tMobileNumber)
                var tEmail=view.findViewById<EditText>(R.id.tEmail)
                var tPassword=view.findViewById<EditText>(R.id.tPasword)
                var tSubmitBtn=view.findViewById<Button>(R.id.tSubmit)
                tSubmitBtn.setOnClickListener {
                    var name=tName.text.toString()
                    var department=tDepartment.text.toString()
                    var mobileNumber=tMobileNumber.text.toString()
                    var email=tEmail.text.toString()
                    var password=tPassword.text.toString()
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                        if(it.isSuccessful){
                            var refurence=
                                auth.currentUser?.let { it1 ->
                                    FirebaseDatabase.getInstance().getReference(
                                        it1.uid)
                                }
                            var teacherInfo=TeacherModel(name,email,mobileNumber,department)
                            refurence?.setValue(teacherInfo)?.addOnCompleteListener{
                                if(it.isSuccessful){
                                    startActivity(Intent(this@LoginActivity,SelectBusActivity::class.java))
                                    finish()
                                }
                                else{
                                    Toast.makeText(this@LoginActivity,"Failed",Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                }
                teacherDialog.setView(view).setCancelable(true).show()
            }
            stuffBtn.setOnClickListener {
                var stuffDialog =AlertDialog.Builder(this)
                var view=LayoutInflater.from(this).inflate(R.layout.stfprofilemkng,null)
                var departmentName=resources.getStringArray(R.array.stuffDepartemnt)
                var adapter=ArrayAdapter(this@LoginActivity,android.R.layout.simple_expandable_list_item_1,departmentName)
                var stName=view.findViewById<EditText>(R.id.stuffName)
                var stDepartment=view.findViewById<AutoCompleteTextView>(R.id.stuffDepartment)
                stDepartment.setAdapter(adapter)
                var stmobile=view.findViewById<EditText>(R.id.stuffMobile)
                var stemail=view.findViewById<EditText>(R.id.stuffEmail)
                var stPassword=view.findViewById<EditText>(R.id.stuffPassword)
                var stButton=view.findViewById<Button>(R.id.stuffSubmit)
                stButton.setOnClickListener {
                    var name=stName.text.toString()
                    var department=stDepartment.text.toString()
                    var mobileNumber=stmobile.text.toString()
                    var email=stemail.text.toString()
                    var password=stPassword.text.toString()
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                        if(it.isSuccessful){
                            var refurence=
                                auth.currentUser?.let { it1 ->
                                    FirebaseDatabase.getInstance().getReference(
                                        it1.uid)
                                }
                            var teacherInfo=TeacherModel(name,email,mobileNumber,department)
                            refurence?.setValue(teacherInfo)?.addOnCompleteListener{
                                if(it.isSuccessful){
                                    startActivity(Intent(this@LoginActivity,SelectBusActivity::class.java))
                                    finish()
                                }
                                else{
                                    Toast.makeText(this@LoginActivity,"Failed",Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                }
                stuffDialog.setView(view).setCancelable(true).show()
            }
            alertDialog.setView(view).show()
        }
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