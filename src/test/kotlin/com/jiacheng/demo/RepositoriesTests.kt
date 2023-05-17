package com.jiacheng.demo

import com.jiacheng.demo.entity.Article
import com.jiacheng.demo.entity.User
import com.jiacheng.demo.repository.ArticleRepository
import com.jiacheng.demo.repository.UserRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository,
    val articleRepository: ArticleRepository) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val johnNed = User("johnNed", "John", "Ned")
        entityManager.persist(johnNed)
        val article = Article("Title A", "Content A", "Headline A", johnNed)
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`() {
        val johnNed = User("johnNed", "John", "Ned")
        entityManager.persist(johnNed)
        entityManager.flush()
        val user = userRepository.findByLogin(johnNed.login)
        assertThat(user).isEqualTo(johnNed)
    }
}