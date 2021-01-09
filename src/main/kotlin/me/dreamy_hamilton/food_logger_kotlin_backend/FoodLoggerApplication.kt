package me.dreamy_hamilton.food_logger_kotlin_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FoodLoggerApplication

fun main(args: Array<String>) {
    runApplication<FoodLoggerApplication>(*args)
}