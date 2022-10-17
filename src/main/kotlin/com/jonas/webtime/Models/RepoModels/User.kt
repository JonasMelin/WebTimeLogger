package com.jonas.webtime.Models.RepoModels
import jakarta.persistence.*

@Entity
@Table(
    name = "users",
    uniqueConstraints = [
        (
            UniqueConstraint(
                name="firstLastToken",
                columnNames = ["first_name", "last_name", "token"]
            ))
    ])
class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0

    @Column(name = "token", nullable = false)
    var token: String = ""

    @Column(name = "first_name", nullable = false)
    var firstName: String = ""

    @Column(name = "last_name", nullable = false)
    var lastName: String = ""

    constructor() {}
    constructor(firstName: String, lastName: String, token: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.token = token
    }
}