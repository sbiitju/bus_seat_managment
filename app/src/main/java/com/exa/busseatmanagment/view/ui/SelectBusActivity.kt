package com.exa.busseatmanagment.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.exa.busseatmanagment.R
import com.exa.busseatmanagment.databinding.ActivityLoginBinding
import com.exa.busseatmanagment.databinding.ActivitySelectBusActivityBinding
import com.exa.busseatmanagment.utill.CommonListener
import com.exa.busseatmanagment.utill.Utility.showToast
import com.exa.busseatmanagment.viewmodel.LoginViewModel
import com.exa.busseatmanagment.viewmodel.SelectBusViewModel
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth

class SelectBusActivity : AppCompatActivity(),CommonListener{
    lateinit var viewModel: SelectBusViewModel
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var progressBar: ProgressBar
    var binding: ActivitySelectBusActivityBinding? =null
    private lateinit var callbackManager: CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySelectBusActivityBinding.inflate(layoutInflater)
        viewModel= ViewModelProvider(this)[SelectBusViewModel::class.java]
        setContentView(binding!!.root)
        binding!!.model=viewModel
        viewModel.commonListener=this
    }

    override fun onResume() {
        super.onResume()
        var fromto=resources.getStringArray(R.array.fromto)
        var bustype=resources.getStringArray(R.array.bustype)
        var timeArray=resources.getStringArray(R.array.time)
        var busRoute=resources.getStringArray(R.array.busroute)
        var fromtoAdapter= ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,fromto)
        var bustypeAdapter= ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,bustype)
        var timeAdapter= ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,timeArray)
        var busrouteAdapter= ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,timeArray)
        binding?.departure?.setAdapter(fromtoAdapter)
        binding?.type?.setAdapter(bustypeAdapter)
        binding?.time?.setAdapter(timeAdapter)
        binding?.route?.setAdapter(busrouteAdapter)
    }

    override fun onSuccess(msg: String) {
        var intent=Intent(this,MainActivity::class.java)
        intent.putExtra("reference",msg)
        startActivity(intent)
    }

    override fun onFailed(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onNavigate() {
        startActivity(Intent(this,MapsActivity::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}