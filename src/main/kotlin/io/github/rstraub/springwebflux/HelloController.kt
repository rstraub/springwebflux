package io.github.rstraub.springwebflux

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class HelloController(val helloService: HelloService) {
    @GetMapping
    fun hello() = Mono.just(helloService.getHello())
}