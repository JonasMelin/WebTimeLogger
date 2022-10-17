package com.jonas.demo2.repository

import com.jonas.demo2.Models.RepoModels.Project
import com.jonas.demo2.Models.RepoModels.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : JpaRepository<User?, Long?> {
    fun findFirstByFirstNameAndLastNameAndToken(first_name: String, last_name: String, token: String): User?
}

@Repository
interface ProjectRepo : JpaRepository<Project?, Long?>