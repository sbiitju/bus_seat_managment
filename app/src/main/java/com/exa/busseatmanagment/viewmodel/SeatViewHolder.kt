package com.exa.busseatmanagment.viewmodel

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exa.busseatmanagment.R
import com.google.android.material.button.MaterialButton

class SeatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var seatButton=itemView.findViewById(R.id.seatBtn) as Button
    var status=itemView.findViewById(R.id.status) as TextView
}