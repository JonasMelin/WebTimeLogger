package com.jonas.webtime.Models.DTO

class UpdateLoggingDTO(firstName: String, lastName: String, token: String,
                       var projectName: String, var activityType: String, var timeoutMin: Long) :
    UserBaseDTO(firstName, lastName, token){

    val MAX_TIMEOUT_MIN = 24 * 60

    init {
        projectName = projectName.trim().lowercase()
        activityType = activityType.trim().lowercase()

        if (activityType.length < MIN_LEN || projectName.length < MIN_LEN) {
            throw Exception("activityType/projectName must be at least $MIN_LEN characters")
        }
        if (timeoutMin < 1 || timeoutMin > MAX_TIMEOUT_MIN) {
            throw Exception("timeoutMin must be less than $MAX_TIMEOUT_MIN minutes...")
        }
    }
}