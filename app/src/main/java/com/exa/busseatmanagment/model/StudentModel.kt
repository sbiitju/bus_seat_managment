package com.exa.busseatmanagment.model

class StudentModel {
    var name:String=""
    var number:String=""
    var Department:String=""
    var email:String=""
    var regNumber:String=""
    var classRoll:String=""
    var examRoll:String=""

    constructor(
        name: String,
        number: String,
        Department: String,
        email: String,
        regNumber: String,
        classRoll: String,
        examRoll: String
    ) {
        this.name = name
        this.number = number
        this.Department = Department
        this.email = email
        this.regNumber = regNumber
        this.classRoll = classRoll
        this.examRoll = examRoll
    }

    constructor()
}