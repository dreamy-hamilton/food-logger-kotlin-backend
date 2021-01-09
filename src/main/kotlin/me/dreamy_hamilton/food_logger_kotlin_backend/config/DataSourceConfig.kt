package me.dreamy_hamilton.food_logger_kotlin_backend.config

import javax.sql.DataSource
import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class DataSourceConfig(
    private val environment: Environment
) {
    @Value("\${spring.datasource.url}")
    private lateinit var dataSourceUrl: String

    @Value("\${spring.datasource.username}")
    private lateinit var dataSourceUsername: String

    @Value("\${spring.datasource.password}")
    private lateinit var dataSourcePassword: String

    @Bean
    fun dataSourceAfterFlyway(dataSource: DataSource): DataSourceHolder {
        val flyway = Flyway.configure()
            .dataSource(dataSourceUrl, dataSourceUsername, dataSourcePassword)
            .load()
        flyway.migrate()
        return DataSourceHolder(dataSource)
    }

    class DataSourceHolder(val dataSource: DataSource)
}