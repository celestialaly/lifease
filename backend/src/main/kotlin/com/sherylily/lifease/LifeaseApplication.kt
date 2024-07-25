package com.sherylily.lifease

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class LifeaseApplication

fun main(args: Array<String>) {
    runApplication<LifeaseApplication>(*args)
}

@Bean
fun corsConfigurer(): WebMvcConfigurer {
    return object : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            registry.addMapping("/**").allowedOrigins("http://localhost:3000");
        }
    }
}