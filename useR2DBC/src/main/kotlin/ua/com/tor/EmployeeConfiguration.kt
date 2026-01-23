package ua.com.tor

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.function.DatabaseClient
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory
import org.springframework.data.relational.core.mapping.RelationalMappingContext
import org.springframework.web.reactive.function.client.WebClient
import ua.com.tor.repo.EmployeeRepository

@Configuration
class EmployeeConfiguration {
    @Bean
    fun repository(factory: R2dbcRepositoryFactory): EmployeeRepository {
        return factory.getRepository(EmployeeRepository::class.java)
    }

    @Bean
    fun factory(client: DatabaseClient): R2dbcRepositoryFactory {
        val context = RelationalMappingContext()
        context.afterPropertiesSet()
        return R2dbcRepositoryFactory(client, context)
    }

    @Bean
    fun databaseClient(factory: ConnectionFactory): DatabaseClient {
        return DatabaseClient.builder().connectionFactory(factory).build()
    }

    @Bean
    fun connectionFactory(): PostgresqlConnectionFactory {
        val config = PostgresqlConnectionConfiguration.builder()
                .host("192.168.0.44")
                .port(5432)
                .database("acco2018")
                .username("postgres")
                .password("master")
                .build()
        return PostgresqlConnectionFactory(config)
    }

    @Bean
    fun clientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }
}