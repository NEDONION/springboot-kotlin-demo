package com.jiacheng.demo

import com.jiacheng.demo.config.DemoProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(DemoProperties::class)
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
