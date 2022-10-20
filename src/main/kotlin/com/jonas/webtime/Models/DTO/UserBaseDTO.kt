package com.jonas.webtime.Models.DTO

open class UserBaseDTO(var token: String) {

    val MIN_TOKEN_LEN = 32
    val MIN_NAME_LEN = 2

    init {
        token = token.trim().lowercase()

        if (token.length < MIN_TOKEN_LEN) {
            throw Exception("token must be at least $MIN_TOKEN_LEN characters")
        }
    }
}