package com.jonas.webtime

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebTimeApplication

fun main(args: Array<String>) {
	runApplication<WebTimeApplication>(*args)
}
