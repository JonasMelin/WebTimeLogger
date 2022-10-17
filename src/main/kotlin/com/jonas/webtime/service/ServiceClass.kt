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
import java.sql.Time

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

    fun uppdateLogging(firstName: String, lastName: String, token: String,
                       projectName: String, activityType: String, timeout: Int) {

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
            throw RuntimeException("Project not found ($project)...")
        }

        this.timeLogRepo.save(TimeLog(Time(0L), null, Time(0L), true, user, activity, project))
    }

    fun addProject(firstName: String, lastName: String, token: String, projectName: String) {

        val user = this.userDb.findByFirstNameAndLastNameAndToken(firstName, lastName, token)

        if (user == null) {
            throw RuntimeException("User not found...")
        }

        this.projectDb.save(Project(projectName, user))
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