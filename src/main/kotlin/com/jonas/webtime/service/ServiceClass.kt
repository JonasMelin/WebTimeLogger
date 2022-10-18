package com.jonas.webtime.service

import com.jonas.webtime.Models.RepoModels.Activity
import com.jonas.webtime.Models.RepoModels.Project
import com.jonas.webtime.Models.RepoModels.TimeLog
import com.jonas.webtime.Models.RepoModels.User
import com.jonas.webtime.repository.ActivityRepo
import com.jonas.webtime.repository.ProjectRepo
import com.jonas.webtime.repository.TimeLogRepo
import com.jonas.webtime.repository.UserRepo
import org.springframework.scheduling.annotation.Scheduled
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
        this.activityDb.save(Activity(activityType, this.getUser(firstName, lastName, token)))
    }

    fun addProject(firstName: String, lastName: String, token: String, projectName: String) {
        this.projectDb.save(Project(projectName, this.getUser(firstName, lastName, token)))
    }

    fun uppdateLogging(firstName: String, lastName: String, token: String,
                       projectName: String, activityType: String, timeoutMinutes: Long) {

        val user = this.getUser(firstName, lastName, token)
        val activity = this.getActivity(activityType, user)
        val project = this.getProject(projectName, user)

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

    fun getProjects(firstName: String, lastName: String, token: String): Set<Project> {

        return this.projectDb.findByUser(this.getUser(firstName, lastName, token))
    }

    fun getActivities(firstName: String, lastName: String, token: String): Set<Activity> {

        return this.activityDb.findByUser(this.getUser(firstName, lastName, token))
    }

    private fun getUser(firstName: String, lastName: String, token: String): User {
        val user = this.userDb.findByFirstNameAndLastNameAndToken(firstName, lastName, token)

        if (user == null) {
            throw RuntimeException("User not found ($firstName $lastName / $token)...")
        }

        return user
    }

    private fun getActivity(activityType: String, user: User): Activity {
        val activity = this.activityDb.findByActivityTypeAndUser(activityType, user)

        if (activity == null) {
            throw RuntimeException("Activity not found ($activityType)...")
        }

        return activity
    }

    private fun getProject(projectName: String, user: User): Project {
        val project = this.projectDb.findByProjectNameAndUser(projectName, user)

        if (project == null) {
            throw RuntimeException("Project not found ($projectName)...")
        }

        return project
    }

    private fun stopOngoingRecording(user: User) {
        while(true) { // ToDo: Get a Set instead. But, should never happen as only one can be active...
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

    @Scheduled(fixedDelay = 55 * 1000, initialDelay = 5 * 1000)
    fun timerCallback() {

        for(nextTimeLog in timeLogRepo.findByOngoingAndExpireTimeMsLessThan(true, System.currentTimeMillis())) {
            println("Timer expired: (${nextTimeLog.user.firstName} ${nextTimeLog.user.lastName}/${nextTimeLog.activity.activityType}/${nextTimeLog.project.projectName})")
            this.stopOngoingRecording(nextTimeLog)
        }
    }
}