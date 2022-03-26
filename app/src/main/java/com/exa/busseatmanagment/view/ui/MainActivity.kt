package com.exa.busseatmanagment.view.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.exa.busseatmanagment.databinding.ActivityMainBinding
import com.exa.busseatmanagment.model.data_class.SeatModel
import com.exa.busseatmanagment.view.adapter.BusSeatAdapter
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var seatlist:ArrayList<SeatModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var progressDialog=ProgressDialog(this)
        progressDialog.setTitle("Getting Data...")
        progressDialog.setCancelable(false)
        progressDialog.show()
        supportActionBar?.hide()
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        seatlist=ArrayList()
        var arg=intent.getStringExtra("reference")
        var databaseReference1= arg?.let { FirebaseDatabase.getInstance().getReference(it) }
        databaseReference1?.child("seat")?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    seatlist= snapshot.getValue<ArrayList<SeatModel>>()!!
                    Log.d("Check",seatlist.toString())

                    var b=BusSeatAdapter(this@MainActivity,seatlist,arg) {
                        Log.d("Check",it)
                    }
                    binding.recyclerview.layoutManager=GridLayoutManager(this@MainActivity,4)
                    binding.recyclerview.adapter=b
                    progressDialog.hide()
                }else{
                    Toast.makeText(this@MainActivity,"No Data Found", Toast.LENGTH_LONG).show()
                    progressDialog.hide()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Failed",error.toString())
            }

        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,SelectBusActivity::class.java))
        finish()
    }
}