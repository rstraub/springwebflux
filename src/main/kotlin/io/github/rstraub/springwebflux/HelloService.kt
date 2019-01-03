package io.github.rstraub.springwebflux

import org.springframework.stereotype.Service

@Service
class HelloService {
    fun getHello() = HelloDTO("Hello World")
}