@file:Suppress("PackageDirectoryMismatch")

package com.knowledgespike.scores.repository

import com.knowledgespike.repository.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*

class UsersRepository : Repository<User> {
    override suspend fun get(): Iterable<User> = Users.selectAll().map { toUser(it) }

    override suspend fun update(entity: User): Int {
        val id = entity.id
        return if (id == null) {
            add(entity)
        } else {
            dbQuery {
                Users.update({ Users.id eq id }) {
                    it[firstName] = entity.firstName
                    it[lastName] = entity.lastName
                    it[email] = entity.email
                    it[score] = entity.score
                }
            }
        }
    }

    override suspend fun add(entity: User): Int {
        return dbQuery {
            (Users.insert {
                it[firstName] = entity.firstName
                it[lastName] = entity.lastName
                it[email] = entity.email
                it[score] = entity.score
            } get Users.id)!!
        }
    }

    override suspend fun delete(entity: User) {
        return dbQuery {
            Users.deleteWhere { Users.id eq entity.id!! } > 0
        }
    }

    private fun toUser(row: ResultRow): User =
        User(
            id = row[Users.id],
            email = row[Users.email],
            firstName = row[Users.firstName],
            lastName = row[Users.lastName],
            score = row[Users.score]
        )
}

interface Repository<T> {
    suspend fun get(): Iterable<T>
    suspend fun update(entity: T): Int
    suspend fun add(entity: T): Int
    suspend fun delete(entity: T)

}



