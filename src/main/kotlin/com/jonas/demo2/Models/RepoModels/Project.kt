package com.jonas.demo2.Models.RepoModels
import jakarta.persistence.*

@Entity
@Table(name = "projects")
class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val project_id : Long = 0

    @Column(name = "project_name", nullable = false)
    var projectName: String = ""

    //@ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name = "users", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "user_id", nullable = false)
    var userId: Long = 0

    constructor() {}
    constructor(projectName: String, userId: Long) {
        this.projectName = projectName
        this.userId = userId
    }
}