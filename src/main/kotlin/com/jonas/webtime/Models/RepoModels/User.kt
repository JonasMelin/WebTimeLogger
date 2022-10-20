package com.jonas.webtime.Models.RepoModels
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0

    @Column(name = "token", nullable = false, unique = true)
    var token: String = ""

    @Column(name = "first_name", nullable = false)
    var firstName: String = ""

    @Column(name = "last_name", nullable = false)
    var lastName: String = ""

    @Column(name = "email", nullable = false)
    var email: String = ""

    constructor() {}
    constructor(firstName: String, lastName: String, email: String, token: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.token = token
    }
}