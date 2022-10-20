package com.jonas.webtime.Models.DTO

class AddUserReplyDTO {

    var token: String? = ""
    var message: String? = "ok"

    constructor()  {}
    constructor(token: String?, message: String?) {
        this.token = token
        this.message = message
    }
}