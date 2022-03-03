package com.exa.busseatmanagment.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.exa.busseatmanagment.R
import com.exa.busseatmanagment.databinding.ActivityMainBinding
import com.exa.busseatmanagment.model.data_class.SeatModel
import com.exa.busseatmanagment.model.data_class.User
import com.exa.busseatmanagment.view.adapter.BusSeatAdapter

class MainActivity : AppCompatActivity() {
    lateinit var seatlist:ArrayList<SeatModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Shahin Bashar","On create")
        supportActionBar?.hide()
        val binding=ActivityMainBinding.inflate(layoutInflater)
        val arg=intent.getStringExtra("reference")
        setContentView(binding.root)
        var user= User("Shahin Bashar","0163","dfjak","","","")
        Log.d("Shahin Bashar",user.toString())
        seatlist=ArrayList()
        var a=SeatModel("Abc","0","Available",resources.getColor(R.color.white))
        var bb=SeatModel("Abc","4","Reserved",resources.getColor(R.color.red))
        var c=SeatModel("Abc","9","Processing",resources.getColor(R.color.green))
        seatlist.add(a)
        seatlist.add(bb)
        seatlist.add(c)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(c)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(a)
        seatlist.add(bb)
        seatlist.add(30,c)
        seatlist.add(0,bb)
        var b:BusSeatAdapter=BusSeatAdapter(this,seatlist,arg)
        binding.recyclerview.layoutManager=GridLayoutManager(this,2)
        binding.recyclerview2.layoutManager=GridLayoutManager(this,2)
        binding.recyclerview.adapter=b
        binding.recyclerview2.adapter=b
    }

    override fun onStart() {
        super.onStart()
        Log.d("Shahin Bashar","On start")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Shahin Bashar","On stop")

    }

    override fun onResume() {
        super.onResume()
        Log.d("Shahin Bashar","On resume")

    }

    override fun onPause() {
        super.onPause()
        Log.d("Shahin Bashar","On pause")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Shahin Bashar","On restart")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Shahin Bashar","On destroy")

    }
}