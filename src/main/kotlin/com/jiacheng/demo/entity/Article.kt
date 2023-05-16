package com.jiacheng.demo.entity

import com.jiacheng.demo.utils.toSlug
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Article (
    @Id
    @GeneratedValue
    var id: Long? = null,
    var title: String,
    var content: String,
    var headline: String,
    @ManyToOne var author: User,
    var slug: String = title.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now()
)