package com.jonas.webtime.controller

import com.jonas.webtime.Models.DTO.AddActivityDTO
import com.jonas.webtime.Models.DTO.AddProjectDTO
import com.jonas.webtime.Models.DTO.AddUserDTO
import com.jonas.webtime.Models.DTO.UpdateLoggingDTO
import com.jonas.webtime.service.ServiceClass
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HtmlController (private val serviceClass: ServiceClass) {

    @GetMapping("/")
    fun hello(model: Model): String {
        return "hello!"
    }

    @PostMapping("/api/v1/add_user")
    fun addUser(@RequestBody userDto: AddUserDTO): ResponseEntity<String> {
        try {
            this.serviceClass.addUser(userDto.firstName, userDto.lastName, userDto.token)
            return ResponseEntity("", HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity("Could not add user: " + ex.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/api/v1/add_project")
    fun addProject(@RequestBody projectDto: AddProjectDTO): ResponseEntity<String> {
        try {
            this.serviceClass.addProject(projectDto.firstName, projectDto.lastName, projectDto.token, projectDto.projectName)
            return ResponseEntity("", HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity("Could not add project: " + ex.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/api/v1/add_activity")
    fun addActivity(@RequestBody activityDTO: AddActivityDTO): ResponseEntity<String> {
        try {
            this.serviceClass.addActivity(activityDTO.firstName, activityDTO.lastName,
                activityDTO.token, activityDTO.activityType)
            return ResponseEntity("", HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity("Could not add activity: " + ex.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/api/v1/update_logging")
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