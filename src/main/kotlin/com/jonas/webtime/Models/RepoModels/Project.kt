package com.jonas.webtime.Models.RepoModels
import jakarta.persistence.*

@Entity
@Table(
    name = "projects",
    uniqueConstraints = [
        (
                UniqueConstraint(
                    name="nameUserId",
                    columnNames = ["project_name", "user_id"]
                ))
    ]
)
class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0

    @Column(name = "project_name", nullable = false)
    var projectName: String = ""

    @ManyToOne
    var user: User = User()

    constructor() {}
    constructor(projectName: String, user: User) {
        this.projectName = projectName
        this.user = user
    }
}