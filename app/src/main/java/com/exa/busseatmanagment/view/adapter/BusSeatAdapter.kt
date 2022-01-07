package com.exa.busseatmanagment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exa.busseatmanagment.R
import com.exa.busseatmanagment.model.SeatModel
import com.exa.busseatmanagment.viewmodel.SeatViewHolder

class BusSeatAdapter(var context: Context,var seatList: List<SeatModel>): RecyclerView.Adapter<SeatViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.seatlist,null)
        return SeatViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        holder.seatButton.setText("${position+1}")
    }

    override fun getItemCount(): Int {
        return  seatList.size
    }
}