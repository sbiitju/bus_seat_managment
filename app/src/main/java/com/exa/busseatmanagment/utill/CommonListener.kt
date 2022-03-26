package com.exa.busseatmanagment.utill

interface CommonListener {
    fun onSuccess(msg: String,int: Int)
    fun onFailed(msg:String)
    fun onNavigate(s: String)
}