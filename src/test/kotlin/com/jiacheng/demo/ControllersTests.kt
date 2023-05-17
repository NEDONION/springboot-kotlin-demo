package com.jiacheng.demo

import com.jiacheng.demo.entity.Article
import com.jiacheng.demo.entity.User
import com.jiacheng.demo.repository.ArticleRepository
import com.jiacheng.demo.repository.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
class ControllersTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var articleRepository: ArticleRepository

    @MockkBean
    lateinit var userRepository: UserRepository

    @Test
    fun `List articles`() {
        val johnNed = User("johnNed", "John", "Ned")
        val aArticle = Article("Title A", "Content A", "Headline A", johnNed)
        val bArticle = Article("Title B", "Content B", "Headline B", johnNed)
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(aArticle, bArticle)
        mockMvc.perform(MockMvcRequestBuilders.get("/api/article/").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].author.login").value(johnNed.login))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].slug").value(aArticle.slug))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].author.login").value(johnNed.login))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].slug").value(bArticle.slug))
    }

    @Test
    fun `List users`() {
        val johnNed = User("johnNed", "John", "Ned")
        val RichardHu = User("RichardHu", "Richard", "Hu")
        every { userRepository.findAll() } returns listOf(johnNed, RichardHu)
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].login").value(johnNed.login))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].login").value(RichardHu.login))
    }
}