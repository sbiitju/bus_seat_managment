package com.exa.busseatmanagment.model.data_class

class SeatModel{
    var busName:String="";
    var seatNumber:String="";
    var staus:String="";
    var color:Int=0;

    constructor(busName: String, seatNumber: String, staus: String, color: Int) {
        this.busName = busName
        this.seatNumber = seatNumber
        this.staus = staus
        this.color = color
    }

    constructor()
}
