package me.dreamy_hamilton.food_logger_kotlin_backend

import me.dreamy_hamilton.food_logger_kotlin_backend.model.Product
import me.dreamy_hamilton.food_logger_kotlin_backend.testbase.IntegrationTestBase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CompleteScenarioTest : IntegrationTestBase(){

    @Test
    fun `A post request should create a database entry that can be retrieved by a get request`() {
        val productId = 0
        val product = Product(productId, "test name")
        testClient.postProduct(product)
        val foundProduct = testClient.getProductById(productId)
        Assertions.assertEquals(product, foundProduct)
    }
}