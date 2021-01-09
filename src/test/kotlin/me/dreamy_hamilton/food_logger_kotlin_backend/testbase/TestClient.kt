package me.dreamy_hamilton.food_logger_kotlin_backend.testbase

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import me.dreamy_hamilton.food_logger_kotlin_backend.model.Product
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class TestClient(port: String) {
    private val mapper = jacksonObjectMapper()
    private val client = OkHttpClient()

    private val productsUrl = "http://localhost:$port/products"

    fun getProductById(id: Int): Product? {
        val request = Request.Builder()
            .url("$productsUrl/$id")
            .build()
        val productJson = client.newCall(request).execute().body?.string() ?: return null
        return mapper.readValue(productJson)
    }

    fun postProduct(product: Product) {
        val request = Request.Builder()
            .url(productsUrl)
            .post(mapper.writeValueAsString(product).toRequestBody("application/json".toMediaType()))
            .build()
        val response = client.newCall(request).execute()
        if(!response.isSuccessful) throw Exception("Posting product failed: $response")
    }
}