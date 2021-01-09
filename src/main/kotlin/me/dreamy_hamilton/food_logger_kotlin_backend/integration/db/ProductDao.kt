package me.dreamy_hamilton.food_logger_kotlin_backend.integration.db

import me.dreamy_hamilton.food_logger_kotlin_backend.model.Product
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.springframework.stereotype.Component
import javax.sql.DataSource
import me.dreamy_hamilton.food_logger_kotlin_backend.config.DataSourceConfig.DataSourceHolder

@Component
class ProductDao(dataSourceHolder: DataSourceHolder) {
    init {
        Database.connect(dataSourceHolder.dataSource)
    }

    fun findByProductId(id: Int): Product? = DbProduct.find { DbProducts.productId eq id }.firstOrNull()?.toProduct()

    fun insert(product: Product) {
        DbProduct.new {
            productId = product.id
            name = product.name
        }
    }
}

class DbProduct(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, DbProduct>(DbProducts)

    var productId by DbProducts.productId
    var name by DbProducts.name
}

fun DbProduct.toProduct() = Product(productId, name)