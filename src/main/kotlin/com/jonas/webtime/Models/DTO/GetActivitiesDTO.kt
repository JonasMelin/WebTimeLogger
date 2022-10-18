package com.jonas.webtime.Models.DTO

class GetActivitiesDTO {

    var activityTypes: MutableList<String>? = mutableListOf()
    var message: String? = "ok"

    constructor()  {}
    constructor(activityTypes: MutableList<String>?, message: String?) {
        this.activityTypes = activityTypes
        this.message = message
    }
}