package com.exa.busseatmanagment.view.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.exa.busseatmanagment.R
import com.exa.busseatmanagment.model.data_class.SeatModel
import com.exa.busseatmanagment.model.data_class.User
import com.exa.busseatmanagment.viewmodel.SeatViewHolder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

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
        if(seatList[position].staus=="Booked"){
            holder.seatButton.isClickable=false
        }
//        }else{
            holder.seatButton.setText("${position+1}")
//        }
        holder.seatButton.setOnClickListener(View.OnClickListener {
            var alertDialog=AlertDialog.Builder(context)
            var view1=LayoutInflater.from(context).inflate(R.layout.book,null)
            alertDialog.setView(view1)
            var name=view1.findViewById<EditText>(R.id.userName)
            var number=view1.findViewById<EditText>(R.id.userNumber)
            var batch=view1.findViewById<EditText>(R.id.userBatch)
            var n=name.text.toString()
            var b=batch.text.toString()
            var num=number.text.toString()
            var user=User(n,num,b)
            var submitBtn=view1.findViewById<Button>(R.id.userSubmitBtn)

            submitBtn.setOnClickListener(View.OnClickListener {
                databaseReference?.child(position.toString())?.setValue(arg?.let { it1 -> SeatModel(it1,
                    position.toString(),"Booked",R.color.red) })
                var databaseReference1=FirebaseDatabase.getInstance().getReference(arg?.substring(1,10)+position)
                databaseReference1.setValue(user)
            })
            if(seatList[position].staus=="Booked"){
                var databaseReference1=FirebaseDatabase.getInstance().getReference(arg?.substring(1,10)+position)
                databaseReference1.addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            Toast.makeText(context,"Try another seat",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }

                })

            }else{
                alertDialog.show()
            }

        })

    }

    override fun getItemCount(): Int {
        return  80
    }
}