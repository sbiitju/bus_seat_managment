package com.exa.busseatmanagment.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.exa.busseatmanagment.R
import com.exa.busseatmanagment.databinding.ActivityMainBinding
import com.exa.busseatmanagment.model.SeatModel
import com.exa.busseatmanagment.view.adapter.BusSeatAdapter

class MainActivity : AppCompatActivity() {
    lateinit var seatlist:ArrayList<SeatModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        seatlist=ArrayList()
        var a=SeatModel("Abc",true,true)
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


        var b:BusSeatAdapter=BusSeatAdapter(this,seatlist)
        binding.recyclerview.layoutManager=GridLayoutManager(this,4)
        binding.recyclerview.adapter=b
    }
}