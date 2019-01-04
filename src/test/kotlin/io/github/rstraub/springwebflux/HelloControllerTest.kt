package io.github.rstraub.springwebflux

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux

class HelloControllerTest {
    private lateinit var client: WebTestClient

    @Before
    fun setup() {
        val service: HelloService = mock(HelloService::class.java)
        Mockito.`when`(service.getHello()).thenReturn(Flux.empty())
        val controller = HelloController(service)

        client = WebTestClient.bindToController(controller)
            .configureClient()
            .baseUrl("/")
            .build()
    }

    @Test
    fun testHello() {
        client
                .get()
                .exchange()
                .expectStatus()
                .isOk
    }
}