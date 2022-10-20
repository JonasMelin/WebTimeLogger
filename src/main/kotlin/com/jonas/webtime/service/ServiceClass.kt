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
import java.util.*

@Service
class ServiceClass (
    private val userDb: UserRepo,
    private val projectDb: ProjectRepo,
    private val activityDb: ActivityRepo,
    private val timeLogRepo: TimeLogRepo
    ){

    fun addUser(firstName: String, lastName: String, email: String): String {

        val uuid = UUID.randomUUID().toString()
        this.userDb.save(User(firstName, lastName, email, uuid))
        return uuid
    }

    fun addActivity(token: String, activityType: String) {
        this.activityDb.save(Activity(activityType, this.getUser(token)))
    }

    fun addProject(token: String, projectName: String) {
        this.projectDb.save(Project(projectName, this.getUser(token)))
    }

    fun uppdateLogging(token: String, projectName: String, activityType: String, timeoutMinutes: Long) {

        val user = this.getUser(token)
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

    fun getProjects(token: String): Set<Project> {

        return this.projectDb.findByUser(this.getUser(token))
    }

    fun getActivities(token: String): Set<Activity> {

        return this.activityDb.findByUser(this.getUser(token))
    }

    private fun getUser(token: String): User {
        val user = this.userDb.findByToken(token)

        if (user == null) {
            throw RuntimeException("User with token $token not found ...")
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