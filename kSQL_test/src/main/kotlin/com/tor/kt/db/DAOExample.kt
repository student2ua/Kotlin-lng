package com.tor.kt.db

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.statements.BatchInsertStatement
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

object Users2 : IntIdTable() {
    val name = varchar("name", 50).index()
    val city = reference("city", Cityes2)
    val age = integer("age")
}

object Cityes2 : IntIdTable() {
    val name = varchar("name", 50)
}

class User2(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User2>(Users2)

    var name by Users2.name
    var city by City2 referencedOn Users2.city
    var age by Users2.age
}

class City2(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<City2>(Cityes2)

    var name by Cityes2.name
    val users by User2 referrersOn  Users2.city
}

fun main(args: Array<String>) {
    Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
    transaction {
        logger.addLogger(StdOutSqlLogger)
        SchemaUtils.create(Cityes2, Users2)
        val stPete = City2.new { name = "St.Piter" }
        val munich = City2.new { name = "Munich" }
        User2.new {
            name = "a"
            city = stPete
            age = 15
        }
        User2.new {
            name = "b"
            city = munich
            age = 16
        }
     val u =   User2.new {
            name = "c"
            city = stPete
            age = 17
        }
        println("Cityes: ${City2.all().joinToString { it.name }}")
        println("Users in ${stPete.name}: ${stPete.users.joinToString{it.name}}")
        println("Adults: ${User2.find{Users2.age greaterEq 16}.joinToString { it.name }}")
    }

}