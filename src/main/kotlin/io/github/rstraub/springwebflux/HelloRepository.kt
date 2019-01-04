package io.github.rstraub.springwebflux

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface HelloRepository : ReactiveCrudRepository<Hello, String>