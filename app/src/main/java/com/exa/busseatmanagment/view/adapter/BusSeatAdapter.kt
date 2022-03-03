package com.exa.busseatmanagment.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exa.busseatmanagment.R
import com.exa.busseatmanagment.model.data_class.SeatModel
import com.exa.busseatmanagment.viewmodel.SeatViewHolder
import com.google.firebase.database.FirebaseDatabase

class BusSeatAdapter(var context: Context, var seatList: List<SeatModel>, arg: String?): RecyclerView.Adapter<SeatViewHolder>(){
    val arg=arg
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.seatlist,null)
        return SeatViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        var databaseReference= arg?.let { FirebaseDatabase.getInstance().getReference(it) }
//        if(seatList[position].seatNumber.toInt()!=position){
            holder.status.setText(seatList[position].staus)
//            holder.seatButton.setText("${position+1}+$arg")
            holder.seatButton.setBackgroundColor(seatList[position].color)
//        }else{
            holder.seatButton.setText("${position+1}")
//        }
        holder.seatButton.setOnClickListener(View.OnClickListener {
            databaseReference?.child(position.toString())?.setValue(arg?.let { it1 -> SeatModel(it1,
                position.toString(),"Booked",R.color.red) })
        })

    }

    override fun getItemCount(): Int {
        return  40
    }
}