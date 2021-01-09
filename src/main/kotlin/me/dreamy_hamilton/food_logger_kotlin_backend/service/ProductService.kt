package me.dreamy_hamilton.food_logger_kotlin_backend.service

import me.dreamy_hamilton.food_logger_kotlin_backend.integration.db.ProductDao
import me.dreamy_hamilton.food_logger_kotlin_backend.model.Product
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productDao: ProductDao
) {
    fun getProduct(id: Int): Product = transaction {
        productDao.findByProductId(id) ?: throw Exception("Product not found")
    }

    fun saveProduct(product: Product) {
        transaction {
            productDao.insert(product)
        }
    }
}
