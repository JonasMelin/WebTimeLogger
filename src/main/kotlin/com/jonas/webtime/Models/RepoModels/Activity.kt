package com.jonas.webtime.Models.RepoModels
import jakarta.persistence.*

@Entity
@Table(
    name = "activities",
    uniqueConstraints = [
        (
                UniqueConstraint(
                    name="typeUserId",
                    columnNames = ["activity_type", "user_id"]
                ))
    ]
)
class Activity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0

    @Column(name = "activity_type", nullable = false)
    var activityType: String = ""

    @ManyToOne
    var user: User = User()

    constructor() {}
    constructor(activityType: String, user: User) {
        this.activityType = activityType
        this.user = user
    }
}