package com.knowledgespike.scores.service

import com.knowledgespike.scores.repository.UsersRepository

interface IUsersService {
    suspend fun test()
}

class UsersService(private val usersRepository: UsersRepository) : IUsersService {

    override  suspend fun test() {
        usersRepository.get()
    }
}