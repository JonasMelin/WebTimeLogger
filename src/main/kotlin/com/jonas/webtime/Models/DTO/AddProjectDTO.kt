package com.jonas.webtime.Models.DTO

class AddProjectDTO(var firstName: String, var lastName: String, var token: String, var projectName: String) {

    val MIN_LEN = 2

    init {
        firstName = firstName.trim().lowercase()
        lastName = lastName.trim().lowercase()
        token = token.trim()
        projectName = projectName.trim().lowercase()

        if (projectName.length < MIN_LEN) {
            throw Exception("projectName must be at least $MIN_LEN characters")
        }
    }
}