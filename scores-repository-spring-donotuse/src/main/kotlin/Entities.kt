@file:Suppress("PackageDirectoryMismatch")

package com.knowledgespike.scores.repository.entities

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val score: Int
)


