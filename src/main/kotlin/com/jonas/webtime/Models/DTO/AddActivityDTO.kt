package com.jonas.webtime.Models.DTO

class AddActivityDTO(var firstName: String, var lastName: String, var token: String, var activityType: String) {

    val MIN_LEN = 2

    init {
        firstName = firstName.trim().lowercase()
        lastName = lastName.trim().lowercase()
        token = token.trim()
        activityType = activityType.trim().lowercase()

        if (activityType.length < MIN_LEN) {
            throw Exception("activityType must be at least $MIN_LEN characters")
        }
    }
}