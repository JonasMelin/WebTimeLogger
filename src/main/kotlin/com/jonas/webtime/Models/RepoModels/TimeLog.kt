package com.jonas.webtime.Models.RepoModels
import jakarta.persistence.*

@Entity
@Table(name = "timelog")
class TimeLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0

    @Column(name = "start_time_ms", nullable = false)
    var startTimeMs: Long = 0L

    @Column(name = "end_time_ms", nullable = true)
    var endTimeMs: Long? = null

    @Column(name = "expire_time_ms", nullable = true)
    var expireTimeMs: Long? = null

    @Column(name = "logged_time_ms", nullable = true)
    var loggedTimeMs: Long = 0

    @Column(name = "ongoing", nullable = false)
    var ongoing: Boolean = false

    @ManyToOne
    var user: User = User()

    @ManyToOne
    var activity: Activity = Activity()

    @ManyToOne
    var project: Project = Project()

    constructor() {}
    constructor(startTimeMs: Long, endTimeMs: Long?, expireTimeMs: Long?, loggedTimeMs: Long,
                ongoing: Boolean, user: User, activity: Activity, project: Project) {
        this.startTimeMs = startTimeMs
        this.endTimeMs = endTimeMs
        this.expireTimeMs = expireTimeMs
        this.loggedTimeMs = loggedTimeMs
        this.ongoing = ongoing
        this.user = user
        this.activity = activity
        this.project = project
    }
}