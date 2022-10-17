package com.jonas.webtime.Models.RepoModels
import jakarta.persistence.*

@Entity
@Table(name = "activities")
class Activity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val activityId : Long = 0

    @Column(name = "activity_name", nullable = false)
    var activityName: String = ""

    @Column(name = "activity_type", nullable = false)
    var activityType: String = ""

    @Column(name = "project_id", nullable = false)
    @JoinColumn(name = "projects", referencedColumnName = "proj_id")
    var projectId: Long = 0

    @Column(name = "user_id", nullable = false)
    @JoinColumn(name = "users", referencedColumnName = "user_id")
    var userId: String = ""

    constructor() {}
    constructor(activityName: String, activityType: String, projectId: Long, userId: String) {
        this.activityName = activityName
        this.activityType = activityType
        this.projectId = projectId
        this.userId = userId
    }
}