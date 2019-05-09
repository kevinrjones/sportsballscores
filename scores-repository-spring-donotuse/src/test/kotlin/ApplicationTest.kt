@file:Suppress("PackageDirectoryMismatch")

package com.knowledgespike.scores.repository.test

import com.knowledgespike.scores.repository.SportsballConfig
import com.knowledgespike.scores.repository.UsersRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = arrayOf(SportsballConfig::class) )
class JpaCityServiceTest(@Autowired val repository: UsersRepository) {

    @Test
    fun `'Users' should retrieve empty list if repository doesn't contain entities`() {
        assertThat(repository.findAll()).isEmpty()
    }

}