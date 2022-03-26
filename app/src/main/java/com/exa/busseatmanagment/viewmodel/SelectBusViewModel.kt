package com.exa.busseatmanagment.viewmodel

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.exa.busseatmanagment.utill.CommonListener
import com.google.firebase.auth.FirebaseAuth

class SelectBusViewModel(application: Application): AndroidViewModel(application) {
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
        if(placeOfDeparture.value.isNullOrEmpty()||typeOfBus.value.isNullOrEmpty()||root.value.isNullOrEmpty()||time.value.isNullOrEmpty()){
            Toast.makeText(getApplication(),"Please Fill up all the box",Toast.LENGTH_LONG).show()
        }else{
            commonListener?.onSuccess(placeOfDeparture.value.toString()+ typeOfBus.value.toString()+root.value.toString()+time.value.toString(),0)
        }
    }
    fun findBus(view: View){
        commonListener?.onNavigate(placeOfDeparture.value.toString()+ typeOfBus.value.toString()+root.value.toString()+time.value.toString())
    }
    fun shareLocation(view: View){
        commonListener?.onSuccess(
            placeOfDeparture.value.toString()+ typeOfBus.value.toString()+root.value.toString()+time.value.toString(),1)
    }
}