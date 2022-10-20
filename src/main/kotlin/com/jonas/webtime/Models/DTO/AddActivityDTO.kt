package com.jonas.webtime.Models.DTO

class AddActivityDTO(token: String, var activityType: String): UserBaseDTO(token) {

    init {
        activityType = activityType.trim().lowercase()

        if (activityType.length < MIN_NAME_LEN) {
            throw Exception("activityType must be at least $MIN_TOKEN_LEN characters")
        }
    }
}