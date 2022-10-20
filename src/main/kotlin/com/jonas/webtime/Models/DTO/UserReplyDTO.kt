package com.jonas.webtime.Models.DTO

class UserReplyDTO {

    var token: String? = ""
    var firstName: String? = ""
    var lastName: String? = ""
    var message: String? = "ok"

    constructor()  {}
    constructor(token: String?, firstName: String?, lastName: String?, message: String?) {
        this.token = token
        this.message = message
        this.firstName = firstName
        this.lastName = lastName
    }
}