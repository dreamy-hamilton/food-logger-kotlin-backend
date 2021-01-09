package me.dreamy_hamilton.food_logger_kotlin_backend.integration.db

import org.jetbrains.exposed.dao.id.IntIdTable

object DbProducts: IntIdTable() {
    val productId = integer("product_id").index()
    val name = text("name")
}