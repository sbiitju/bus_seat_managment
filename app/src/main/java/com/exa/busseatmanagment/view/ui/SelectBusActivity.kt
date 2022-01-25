package com.exa.busseatmanagment.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exa.busseatmanagment.R

class SelectBusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_bus_activity)
        supportActionBar?.hide()
    }

    fun Search(view: android.view.View) {
        startActivity(Intent(this,MainActivity::class.java))
    }
}