
- java target 1.8
- Kotlin: 1.3.61 (конец 2019) -> 1.3.72
- spring boot  Lovelace-RELEASE (≈ Spring Boot 2.1) ->  2.1.18.RELEASE Spring Framework 5.1
- Reactor BOM: Californium-SR3 (2018)
- R2DBC: 1.0.0.M5 + SNAPSHOT’ы
## TO DO upgrade versions
Если хочется Kotlin 1.4+ — что нужно обновить
Минимальный «нормальный» апгрейд
Компонент	Минимум
- Spring Boot	2.4+
- Spring Framework	5.3+
- Reactor	2020.x
- R2DBC	1.0 GA
- Kotlin	1.5.31 (или выше) 
## spring and java 
- Spring Boot 4.0.x: Requires Java 17 or higher.
- Spring Boot 3.x.x: Requires Java 17 or higher.
- Spring Boot 2.7.x: Requires Java 8 and is compatible up to Java 21. 

### to Read
- https://dzone.com/articles/introduction-to-reactive-apis-with-postgres-r2dbc
- https://github.com/spring-projects/spring-data-examples

piomin/sample-spring-data-webflux 

Before running the tests we need to start Postgres database. Here’s the Docker command used for running Postgres container. It is creating user with password, and setting up default database.
 
 `docker run -d --name postgres -p 5432:5432 -e POSTGRES_USER=reactive -e POSTGRES_PASSWORD=reactive123 -e POSTGRES_DB=reactive postgres`