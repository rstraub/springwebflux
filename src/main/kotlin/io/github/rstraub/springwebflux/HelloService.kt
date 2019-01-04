package io.github.rstraub.springwebflux

import org.springframework.stereotype.Service

@Service
class HelloService(val helloRepository: HelloRepository) {
    fun getHello() = helloRepository.findAll()

    fun saveHello(hello: Hello) = helloRepository.save(hello).flux()
}