package com.exa.busseatmanagment.model

class SeatModel {
    lateinit var busName:String
    var available:Boolean = false
    var booking:Boolean=false

    constructor(busName: String, available: Boolean, booking: Boolean) {
        this.busName = busName
        this.available = available
        this.booking = booking
    }
}
/*lateinit var name:String
    lateinit var number:String
    lateinit var batch:String
    lateinit var department:String
    lateinit var hall:String
    lateinit var rollNumber:String
    lateinit var regNumber:String

 */