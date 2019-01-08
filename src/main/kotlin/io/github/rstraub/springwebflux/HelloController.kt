package io.github.rstraub.springwebflux

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.util.*

@RestController
class HelloController(val helloService: HelloService) {
    @GetMapping
    fun hello() = helloService.getHello()

    @PostMapping()
    fun saveHello(@RequestBody hello: Hello): Flux<Hello> {
        return helloService.saveHello(Hello(UUID.randomUUID().toString(), hello.greeting))
    }
}