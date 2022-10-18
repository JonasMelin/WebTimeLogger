package com.jonas.webtime.Models.DTO

open class UserBaseDTO(var firstName: String, var lastName: String, var token: String) {

    val MIN_LEN = 2

    init {
        firstName = firstName.trim().lowercase()
        lastName = lastName.trim().lowercase()
        token = token.trim()

        if (firstName.length < MIN_LEN || lastName.length < MIN_LEN || token.length < MIN_LEN) {
            throw Exception("names must be at least $MIN_LEN characters")
        }
    }
}