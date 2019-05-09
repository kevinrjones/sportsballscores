@file:Suppress("PackageDirectoryMismatch")

package com.knowledgespike.scores.repository

import com.zaxxer.hikari.HikariDataSource
import com.zaxxer.hikari.HikariConfig
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource


@Suppress("ContextJavaBeanUnresolvedMethodsInspection")
@SpringBootConfiguration
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = ["com.knowledgespike"])
//@EnableJpaRepositories("com.knowledgespike.scores.repository")
class SportsballConfig {


    @Bean(destroyMethod = "close")
    fun dataSource(env: Environment): DataSource {
        val dataSourceConfig = HikariConfig()
        dataSourceConfig.driverClassName = env.getRequiredProperty("spring.datasource.driverClassName")
        dataSourceConfig.jdbcUrl = env.getRequiredProperty("spring.datasource.url")
        dataSourceConfig.username = env.getRequiredProperty("spring.datasource.username")
        dataSourceConfig.password = env.getRequiredProperty("spring.datasource.password")

        return HikariDataSource(dataSourceConfig)
    }

    //Add the other beans here

}