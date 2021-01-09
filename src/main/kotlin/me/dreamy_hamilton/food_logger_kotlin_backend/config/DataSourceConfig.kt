package me.dreamy_hamilton.food_logger_kotlin_backend.config

import javax.sql.DataSource
import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataSourceConfig {
    @Bean
    fun dataSourceAfterFlyway(dataSource: DataSource): DataSourceHolder {
        val flyway = Flyway.configure().dataSource(
            System.getenv("SPRING_DATASOURCE_URL"),
            System.getenv("SPRING_DATASOURCE_USERNAME"),
            System.getenv("SPRING_DATASOURCE_PASSWORD")
        ).load()
        flyway.migrate()
        return DataSourceHolder(dataSource)
    }

    class DataSourceHolder(val dataSource: DataSource)
}