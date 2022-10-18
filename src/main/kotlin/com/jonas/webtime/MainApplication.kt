package com.jonas.webtime

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class WebTimeApplication

fun main(args: Array<String>) {
	runApplication<WebTimeApplication>(*args)
}
