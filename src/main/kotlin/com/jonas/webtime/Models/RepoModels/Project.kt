package com.jonas.webtime.Models.RepoModels
import jakarta.persistence.*

@Entity
@Table(name = "projects")
class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0

    @Column(name = "project_name", nullable = false)
    var projectName: String = ""

    @ManyToOne
    var user: User = User()

    @ManyToOne
    var activity: Activity = Activity()

    constructor() {}
    constructor(projectName: String, user: User, activity: Activity) {
        this.projectName = projectName
        this.user = user
        this.activity = activity
    }
}