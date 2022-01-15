package com.exa.busseatmanagment.utill

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.Toast

object Utility {
    /* Making toast
     */
    fun Context.showToast(msg:String){
        val toast:Toast=Toast.makeText(this,msg,Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM,0,0)
        toast.show()
    }
    /*SHow Log view
     */
    fun  Context.makeLog(tag:String,msg:String){
        Log.d(tag,msg)
    }


}