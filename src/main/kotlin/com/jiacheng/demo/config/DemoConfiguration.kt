package com.jiacheng.demo.config

import com.jiacheng.demo.entity.Article
import com.jiacheng.demo.entity.User
import com.jiacheng.demo.repository.ArticleRepository
import com.jiacheng.demo.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DemoConfiguration {

	@Bean
	fun databaseInitializer(userRepository: UserRepository,
							articleRepository: ArticleRepository
	) = ApplicationRunner {

		val johnDoe = userRepository.save(User("johnNed", "John", "Ned"))
		articleRepository.save(Article(
				title = "Title A",
				headline = "Headline A",
				content = "Content A",
				author = johnDoe
		))
		articleRepository.save(
			Article(
				title = "Title B",
				headline = "Headline B",
				content = "Content B",
				author = johnDoe
		)
		)
	}
}
