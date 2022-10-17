package com.jonas.webtime.Models.RepoModels
import jakarta.persistence.*

@Entity
@Table(name = "activities")
class Activity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0

    @Column(name = "activity_name", nullable = false)
    var activityName: String = ""

    @Column(name = "activity_type", nullable = false)
    var activityType: String = ""

    @ManyToOne
    var user: User = User()

    constructor() {}
    constructor(activityName: String, activityType: String, user: User) {
        this.activityName = activityName
        this.activityType = activityType
        this.user = user
    }
}