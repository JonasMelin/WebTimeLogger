package com.jonas.webtime.Models.DTO

class AddProjectDTO(firstName: String, lastName: String, token: String, var projectName: String) :
    UserBaseDTO(firstName, lastName, token) {

    init {
        projectName = projectName.trim().lowercase()

        if (projectName.length < MIN_LEN) {
            throw Exception("projectName must be at least $MIN_LEN characters")
        }
    }
}