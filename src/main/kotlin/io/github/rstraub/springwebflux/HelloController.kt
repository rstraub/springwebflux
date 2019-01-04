package io.github.rstraub.springwebflux

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(val helloService: HelloService) {
    @GetMapping
    fun hello() = helloService.getHello()
}