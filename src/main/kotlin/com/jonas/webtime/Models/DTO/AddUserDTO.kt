package com.jonas.webtime.Models.DTO

open class AddUserDTO(var firstName: String, var lastName: String, var email: String) {

    val MIN_FIRST_LAST_LEN = 2
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

    init {
        firstName = firstName.trim().lowercase()
        lastName = lastName.trim().lowercase()
        email = email.trim().lowercase()

        if (firstName.length < MIN_FIRST_LAST_LEN || lastName.length < MIN_FIRST_LAST_LEN) {
            throw Exception("first/last names must be at least $MIN_FIRST_LAST_LEN characters")
        }
        if (!EMAIL_REGEX.toRegex().matches(email)) {
            throw Exception("Invalid email: $email")
        }
    }
}