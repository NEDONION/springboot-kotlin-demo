package com.jiacheng.demo.repository

import com.jiacheng.demo.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface UserRepository : CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}