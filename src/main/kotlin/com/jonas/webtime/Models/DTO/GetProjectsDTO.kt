package com.jonas.webtime.Models.DTO

class GetProjectsDTO {

    var projects: MutableList<String>? = mutableListOf()
    var message: String? = "ok"

    constructor()  {}
    constructor(projects: MutableList<String>?, message: String?) {
        this.projects = projects
        this.message = message
    }
}