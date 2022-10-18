package com.jonas.webtime.controller

import com.jonas.webtime.Models.DTO.*
import com.jonas.webtime.service.ServiceClass
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller (private val serviceClass: ServiceClass) {

    @GetMapping("/")
    fun hello(model: Model): String {
        return "hello!"
    }

    @PostMapping("/api/v1/user")
    fun addUser(@RequestBody userBaseDto: UserBaseDTO): ResponseEntity<String> {
        try {
            this.serviceClass.addUser(userBaseDto.firstName, userBaseDto.lastName, userBaseDto.token)
            return ResponseEntity("", HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity("Could not add user: " + ex.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/api/v1/project")
    fun addProject(@RequestBody projectDto: AddProjectDTO): ResponseEntity<String> {
        try {
            this.serviceClass.addProject(projectDto.firstName, projectDto.lastName, projectDto.token, projectDto.projectName)
            return ResponseEntity("", HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity("Could not add project: " + ex.message, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/api/v1/project")
    fun getProjects(@RequestBody userBaseDto: UserBaseDTO): ResponseEntity<GetProjectsDTO> {
        try {
            val projects = this.serviceClass.getProjects(userBaseDto.firstName, userBaseDto.lastName, userBaseDto.token)
            val projectsDto = GetProjectsDTO()

            for(nextProj in projects) {
                projectsDto.projects!!.add(nextProj.projectName)
            }

            return ResponseEntity(projectsDto, HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity(GetProjectsDTO(null, "Could not get projects: " + ex.message),
                HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/api/v1/activity")
    fun addActivity(@RequestBody activityDTO: AddActivityDTO): ResponseEntity<String> {
        try {
            this.serviceClass.addActivity(activityDTO.firstName, activityDTO.lastName,
                activityDTO.token, activityDTO.activityType)
            return ResponseEntity("", HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity("Could not add activity: " + ex.message, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/api/v1/activity")
    fun getActivities(@RequestBody userBaseDto: UserBaseDTO): ResponseEntity<GetActivitiesDTO> {
        try {
            val activities = this.serviceClass.getActivities(userBaseDto.firstName, userBaseDto.lastName, userBaseDto.token)
            val activitiesDTO = GetActivitiesDTO()

            for(nextActivity in activities) {
                activitiesDTO.activityTypes!!.add(nextActivity.activityType)
            }

            return ResponseEntity(activitiesDTO, HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity(GetActivitiesDTO(null, "Could not get activities: " + ex.message),
                HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/api/v1/logging")
    fun updateLogging(@RequestBody updateLoggingDTO: UpdateLoggingDTO): ResponseEntity<String> {
        try {
            this.serviceClass.uppdateLogging(updateLoggingDTO.firstName, updateLoggingDTO.lastName,
                updateLoggingDTO.token, updateLoggingDTO.projectName, updateLoggingDTO.activityType,
                updateLoggingDTO.timeoutMin)
            return ResponseEntity("", HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity("Could not update logging: " + ex.message, HttpStatus.BAD_REQUEST)
        }
    }
}