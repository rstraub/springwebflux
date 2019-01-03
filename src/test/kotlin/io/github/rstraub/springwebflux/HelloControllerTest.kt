package io.github.rstraub.springwebflux

import org.junit.Test
import org.springframework.test.web.reactive.server.WebTestClient

class HelloControllerTest {
    private val client = WebTestClient.bindToController(HelloController(HelloService()))
            .configureClient()
            .baseUrl("/")
            .build()

    @Test
    fun testHello() {
        client
                .get()
                .exchange()
                .expectStatus()
                .isOk
    }
}