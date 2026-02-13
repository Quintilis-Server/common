package org.quintilis.common

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.persistence.autoconfigure.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class CommonApplication

fun main(args: Array<String>) {
    runApplication<CommonApplication>(*args)
}
