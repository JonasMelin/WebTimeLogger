package com.jonas.webtime.service

import com.jonas.webtime.Models.RepoModels.Activity
import com.jonas.webtime.Models.RepoModels.Project
import com.jonas.webtime.Models.RepoModels.TimeLog
import com.jonas.webtime.Models.RepoModels.User
import com.jonas.webtime.repository.ActivityRepo
import com.jonas.webtime.repository.ProjectRepo
import com.jonas.webtime.repository.TimeLogRepo
import com.jonas.webtime.repository.UserRepo
import org.springframework.stereotype.Service

@Service
class ServiceClass (
    private val userDb: UserRepo,
    private val projectDb: ProjectRepo,
    private val activityDb: ActivityRepo,
    private val timeLogRepo: TimeLogRepo
    ){

    fun addUser(firstName: String, lastName: String, token: String) {
        this.userDb.save(User(firstName, lastName, token))
    }

    fun addActivity(firstName: String, lastName: String, token: String, activityType: String) {

        val user = this.userDb.findByFirstNameAndLastNameAndToken(firstName, lastName, token)

        if (user == null) {
            throw RuntimeException("User not found...")
        }

        this.activityDb.save(Activity(activityType, user))
    }

    fun addProject(firstName: String, lastName: String, token: String, projectName: String) {

        val user = this.userDb.findByFirstNameAndLastNameAndToken(firstName, lastName, token)

        if (user == null) {
            throw RuntimeException("User not found...")
        }

        this.projectDb.save(Project(projectName, user))
    }

    fun uppdateLogging(firstName: String, lastName: String, token: String,
                       projectName: String, activityType: String, timeoutMinutes: Long) {

        val user = this.userDb.findByFirstNameAndLastNameAndToken(firstName, lastName, token)

        if (user == null) {
            throw RuntimeException("User not found ($firstName $lastName / $token)...")
        }

        val activity = this.activityDb.findByActivityTypeAndUser(activityType, user)

        if (activity == null) {
            throw RuntimeException("Activity not found ($activityType)...")
        }
        val project = this.projectDb.findByProjectNameAndUser(projectName, user)

        if (project == null) {
            throw RuntimeException("Project not found ($projectName)...")
        }

        this.stopOngoingRecording(user)

        this.timeLogRepo.save(
            TimeLog(
                System.currentTimeMillis(),
                null,
                System.currentTimeMillis() + timeoutMinutes * 1000 * 60,
                0,
                true,
                user,
                activity,
                project))
    }

    private fun stopOngoingRecording(user: User) {
        while(true) {
            val ongoingTimeLog = this.timeLogRepo.findFirstByUserAndOngoing(user, true)

            if (ongoingTimeLog == null) { return }

            this.stopOngoingRecording(ongoingTimeLog)
        }
    }

    private fun stopOngoingRecording(timelog: TimeLog?) {

        if (timelog == null || !timelog.ongoing) {
            return
        }

        val currentTime = System.currentTimeMillis()

        timelog.ongoing = false
        timelog.endTimeMs = if ((timelog.expireTimeMs != null) && (timelog.expireTimeMs!! < currentTime)) timelog.expireTimeMs!! else currentTime
        timelog.expireTimeMs = null
        timelog.loggedTimeMs = (timelog.endTimeMs!! - timelog.startTimeMs)
        this.timeLogRepo.save(timelog)
    }

    private fun getTimesForLog() {

    }

    fun functionX() {
        val myArray = mutableListOf<Int>();

        myArray.add(7)

        for(nextItem in myArray) {
            println("Listitems: " + nextItem)
        }

        val myHash = mutableMapOf<String, Int>()
        myHash.put("nisse", 7)

        println("HashItems: " + myHash.get("nisse"))

    }
}