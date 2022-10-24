package com.jonas.webtime.controller

import com.jonas.webtime.Models.DTO.*
import com.jonas.webtime.service.ServiceClass
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@RestController
class Controller (private val serviceClass: ServiceClass) {

    @GetMapping("/")
    fun hello(model: Model): String {
        return "hello!"
    }

    @PostMapping("/api/v1/user")
    fun addUser(@RequestBody addUserDTO: AddUserDTO): ResponseEntity<UserReplyDTO> {
        try {
            return ResponseEntity(
                UserReplyDTO(
                    this.serviceClass.addUser(addUserDTO.firstName, addUserDTO.lastName, addUserDTO.email),
                    addUserDTO.firstName,
                    addUserDTO.lastName,
                    "ok"),
                HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity(
                UserReplyDTO(null, null, null, "Could not add user: " + ex.message),
                HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/api/v1/user/{token}")
    fun getUser(@PathVariable(value="token") token: String): ResponseEntity<UserReplyDTO> {
        try {
            val user = this.serviceClass.getUser(token)
            return ResponseEntity(
                UserReplyDTO(
                    user.token,
                    user.firstName,
                    user.lastName,
                    "ok"),
                HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity(
                UserReplyDTO(null, null, null, "Could not get user: " + ex.message),
                HttpStatus.UNAUTHORIZED)
        }
    }

    @PostMapping("/api/v1/project")
    fun addProject(@RequestBody projectDto: AddProjectDTO): ResponseEntity<BaseReplyDTO> {
        try {
            this.serviceClass.addProject(projectDto.token, projectDto.projectName)
            return ResponseEntity(BaseReplyDTO(), HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity(BaseReplyDTO("Could not add project: " + ex.message), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/api/v1/project/{token}")
    fun getProjects(@PathVariable(value="token") token: String): ResponseEntity<GetProjectsDTO> {
        try {
            val projects = this.serviceClass.getProjects(token)
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
    fun addActivity(@RequestBody activityDTO: AddActivityDTO): ResponseEntity<BaseReplyDTO> {
        try {
            this.serviceClass.addActivity(activityDTO.token, activityDTO.activityType)
            return ResponseEntity(BaseReplyDTO(), HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity(BaseReplyDTO("Could not add activity: " + ex.message), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/api/v1/activity/{token}")
    fun getActivities(@PathVariable(value="token") token: String): ResponseEntity<GetActivitiesDTO> {
        try {
            val activities = this.serviceClass.getActivities(token)
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
    fun updateLogging(@RequestBody updateLoggingDTO: UpdateLoggingDTO): ResponseEntity<BaseReplyDTO> {
        try {
            this.serviceClass.uppdateLogging(updateLoggingDTO.token, updateLoggingDTO.projectName,
                updateLoggingDTO.activityType, updateLoggingDTO.timeoutMin)
            return ResponseEntity(BaseReplyDTO(), HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity(BaseReplyDTO("Could not update logging: " + ex.message), HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/api/v1/logging/stop/{token}")
    fun stopLogging(@PathVariable(value="token") token: String): ResponseEntity<BaseReplyDTO> {
        try {
            this.serviceClass.stopOngoingRecording(token)
            return ResponseEntity(BaseReplyDTO(), HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity(BaseReplyDTO("Could not stop logging: " + ex.message), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/api/v1/logging/lastlog/{token}")
    fun getLastLog(@PathVariable(value="token") token: String): ResponseEntity<GetLastLogDTO> {
        try {
            return ResponseEntity(GetLastLogDTO(this.serviceClass.getLastLog(token)), HttpStatus.OK)
        }catch (ex: Exception) {
            return ResponseEntity(GetLastLogDTO("Could not get activities: " + ex.message), HttpStatus.BAD_REQUEST)
        }
    }
}
