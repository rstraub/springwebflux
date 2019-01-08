package io.github.rstraub.springwebflux

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux

/**
 * Getting this to work took some extra work. Mainly setting up the jUnit 5 with the mockito extension.
 * Read more: https://www.baeldung.com/mockito-junit-5-extension
 */
@ExtendWith(MockitoExtension::class)
class HelloControllerTest {
    @Mock
    private lateinit var helloService: HelloService

    @InjectMocks
    private lateinit var controller: HelloController

    private lateinit var client: WebTestClient

    @BeforeEach
    fun setup() {
        Mockito.`when`(helloService.getHello()).thenReturn(Flux.empty())

        client = WebTestClient.bindToController(controller)
                .configureClient()
                .baseUrl("/")
                .build()
    }

    @Test
    fun `test get greetings`() {
        client
                .get()
                .exchange()
                .expectStatus()
                .is5xxServerError
    }
}