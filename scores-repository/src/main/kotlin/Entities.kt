package com.knowledgespike.scores.repository

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val firstName = varchar("first_name", 255)
    val lastName = varchar("last_name", 255)
    val email = varchar("email", 255)
    val score = integer("score")
}

data class User(
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val score: Int
)


