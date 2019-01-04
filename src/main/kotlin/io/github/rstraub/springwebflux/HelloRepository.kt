package io.github.rstraub.springwebflux

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface HelloRepository : ReactiveMongoRepository<Hello, String>