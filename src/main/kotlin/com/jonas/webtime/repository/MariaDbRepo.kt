package com.jonas.webtime.repository

import com.jonas.webtime.Models.RepoModels.Activity
import com.jonas.webtime.Models.RepoModels.Project
import com.jonas.webtime.Models.RepoModels.TimeLog
import com.jonas.webtime.Models.RepoModels.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : JpaRepository<User?, Long?> {
    fun findByFirstNameAndLastNameAndToken(first_name: String, last_name: String, token: String): User?
}

@Repository
interface ActivityRepo : JpaRepository<Activity?, Long?> {
    fun findByActivityTypeAndUser(activity_type: String, user: User): Activity?
}

@Repository
interface ProjectRepo : JpaRepository<Project?, Long?> {
    fun findByProjectNameAndUser(project_name: String, user: User): Project?
}

@Repository
interface TimeLogRepo : JpaRepository<TimeLog?, Long?>

