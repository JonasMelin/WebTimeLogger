package com.jonas.webtime.Models.DTO

class AddProjectDTO(token: String, var projectName: String): UserBaseDTO(token) {

    init {
        projectName = projectName.trim().lowercase()

        if (projectName.length < MIN_NAME_LEN) {
            throw Exception("projectName must be at least $MIN_NAME_LEN characters")
        }
    }
}