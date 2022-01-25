package com.exa.busseatmanagment.model

import com.exa.busseatmanagment.model.data_class.SeatModel

interface SeatInfoShowModel {
    fun updateSeat(seat:SeatModel)
    fun getAvailableSeat()
    fun cancelSeat(seat: SeatModel)
}