package com.jonas.webtime.Models.DTO

class AddActivityDTO(firstName: String, lastName: String, token: String, var activityType: String):
    UserBaseDTO(firstName, lastName, token) {

    init {
        activityType = activityType.trim().lowercase()

        if (activityType.length < MIN_LEN) {
            throw Exception("activityType must be at least $MIN_LEN characters")
        }
    }
}