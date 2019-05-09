@file:Suppress("PackageDirectoryMismatch")

package com.knowledgespike.scores.repository

import com.knowledgespike.scores.repository.entities.User
import javax.inject.Named

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository


@Repository
interface UsersRepository : PagingAndSortingRepository<User, Long>