package com.exa.busseatmanagment.viewmodel

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exa.busseatmanagment.utill.CommonListener
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.coroutineContext

class SelectBusViewModel: ViewModel() {
    var firebase=FirebaseAuth.getInstance()
    var placeOfDeparture= MutableLiveData<String>()
    var typeOfBus=MutableLiveData<String>()
    var root=MutableLiveData<String>()
    var time=MutableLiveData<String>()
    var commonListener: CommonListener?=null
    fun onPlaceofDepartureChange(char: CharSequence,start:Int,end:Int,count:Int){
        placeOfDeparture.postValue(char.toString())
    }
    fun onTypeOfBusChange(char: CharSequence,start:Int,end:Int,count:Int){
        typeOfBus.postValue(char.toString())
    }
    fun onRootChange(char: CharSequence,start:Int,end:Int,count:Int){
        root.postValue(char.toString())
    }
    fun onTime(char: CharSequence,start:Int,end:Int,count:Int){
        time.postValue(char.toString())
    }
    fun selectBus(view: View){
        commonListener?.onSuccess(placeOfDeparture.value.toString()+ typeOfBus.value.toString()+root.value.toString()+time.value.toString())
    }
    fun findBus(view: View){
        commonListener?.onNavigate()
    }
}