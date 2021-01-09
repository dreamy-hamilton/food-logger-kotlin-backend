package me.dreamy_hamilton.food_logger_kotlin_backend.rest

import me.dreamy_hamilton.food_logger_kotlin_backend.model.Product
import me.dreamy_hamilton.food_logger_kotlin_backend.rest.to.ProductTO
import me.dreamy_hamilton.food_logger_kotlin_backend.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/products"])
class ProductController(
    private val productService: ProductService
) {
    @GetMapping(path = ["/{id}"], produces = ["application/json"])
    fun getProduct(@PathVariable id: Int): ResponseEntity<ProductTO> {
        val product = this.productService.getProduct(id)
        return ResponseEntity.ok(product.toProductTO())
    }

    @PostMapping(consumes = ["application/json"])
    fun postProduct(@RequestBody productTO: ProductTO) {
        this.productService.saveProduct(productTO.toProduct())
    }
}

fun Product.toProductTO() = ProductTO(id, name)

fun ProductTO.toProduct() = Product(id, name)