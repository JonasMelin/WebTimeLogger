package com.jonas.webtime.Models.RepoModels
import jakarta.persistence.*
import java.sql.Time

@Entity
@Table(name = "timelog")
class TimeLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0

    @Column(name = "start_time", nullable = false)
    var startTime: Time = Time(0L)

    @Column(name = "end_time", nullable = true)
    var endTime: Time? = null

    @Column(name = "expire_time", nullable = true)
    var expire_time: Time? = Time(0L)

    @Column(name = "ongoing", nullable = false)
    var ongoing: Boolean = false

    @ManyToOne
    var user: User = User()

    @ManyToOne
    var activity: Activity = Activity()

    @ManyToOne
    var project: Project = Project()

    constructor() {}
    constructor(startTime: Time, endTime: Time?, expireTime: Time?, ongoing: Boolean,
                user: User, activity: Activity, project: Project) {
        this.startTime = startTime
        this.endTime = endTime
        this.expire_time = expireTime
        this.ongoing = ongoing
        this.user = user
        this.activity = activity
        this.project = project
    }
}