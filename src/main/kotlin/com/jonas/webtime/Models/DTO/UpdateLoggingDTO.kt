package com.jonas.webtime.Models.DTO

class UpdateLoggingDTO(var firstName: String, var lastName: String, var token: String,
                       var projectName: String, var activityType: String, var timeoutMin: Int) {

    val MIN_LEN = 2
    val MAX_TIMEOUT_MIN = 120

    init {
        firstName = firstName.trim().lowercase()
        lastName = lastName.trim().lowercase()
        token = token.trim()
        projectName = projectName.trim().lowercase()
        activityType = activityType.trim().lowercase()

        if (activityType.length < MIN_LEN || projectName.length < MIN_LEN) {
            throw Exception("activityType/projectName must be at least $MIN_LEN characters")
        }
        if (timeoutMin > MAX_TIMEOUT_MIN) {
            throw Exception("timeoutMin must be less than $MAX_TIMEOUT_MIN minutes...")
        }
    }
}