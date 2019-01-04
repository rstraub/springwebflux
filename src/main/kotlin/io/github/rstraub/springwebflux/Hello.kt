package io.github.rstraub.springwebflux

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Hello(@Id val id: String, val greeting: String)