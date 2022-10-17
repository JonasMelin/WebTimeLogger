package com.jonas.demo2.service

import com.jonas.demo2.Models.RepoModels.Project
import com.jonas.demo2.Models.RepoModels.User
import com.jonas.demo2.repository.ProjectRepo
import com.jonas.demo2.repository.UserRepo
import org.springframework.stereotype.Service

@Service
class ServiceClass (private val userDb: UserRepo, private val projectDb: ProjectRepo){

    fun addUser(firstName: String, lastName: String, token: String) {
        this.userDb.save(User(firstName.trim().lowercase(), lastName.trim().lowercase(), token))
    }

    fun addProject(firstName: String, lastName: String, token: String, projectName: String) {

        val user = this.userDb.findFirstByFirstNameAndLastNameAndToken(firstName, lastName, token)

        if (user == null) {
            throw RuntimeException("User not found...")
        }

        this.projectDb.save(Project(projectName, user.user_id))
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