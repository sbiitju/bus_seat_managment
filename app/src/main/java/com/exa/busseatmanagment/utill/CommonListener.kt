package com.exa.busseatmanagment.utill

interface CommonListener {
    fun onSuccess(msg: String)
    fun onFailed(msg:String)
    fun onNavigate()
}