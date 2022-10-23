package com.jonas.webtime.Models.DTO

class BaseReplyDTO {

    var message: String? = "ok"

    constructor()  {}
    constructor(message: String?) {
        this.message = message
    }
}