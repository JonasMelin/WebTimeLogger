package com.jonas.webtime.Models.DTO

import com.jonas.webtime.Models.RepoModels.Activity
import com.jonas.webtime.Models.RepoModels.Project
import com.jonas.webtime.Models.RepoModels.TimeLog

class GetLastLogDTO {

    var id : Long? = 0
    var startTimeMs: Long? = 0L
    var endTimeMs: Long? = null
    var expireTimeMs: Long? = null
    var loggedTimeMs: Long? = 0
    var ongoing: Boolean? = false
    var activity: String = ""
    var project: String = ""
    var message: String? = "ok"

    constructor()  {}
    constructor(timeLog: TimeLog) {
        this.id = timeLog.id
        this.startTimeMs = timeLog.startTimeMs
        this.endTimeMs = timeLog.endTimeMs
        this.expireTimeMs = timeLog.expireTimeMs
        this.loggedTimeMs = timeLog.loggedTimeMs
        this.ongoing = timeLog.ongoing
        this.activity = timeLog.activity.activityType
        this.project = timeLog.project.projectName
    }
    constructor(message: String) {
        this.message = message
    }
}