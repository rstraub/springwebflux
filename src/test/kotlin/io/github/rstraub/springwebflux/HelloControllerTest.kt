package io.github.rstraub.springwebflux

import com.nhaarman.mockitokotlin2.any
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Flux

/**
 * Getting this to work took some extra work. Mainly setting up the jUnit 5 with the mockito extension.
 * Read more:
 * https://www.baeldung.com/mockito-junit-5-extension
 *
 * Also mockito matchers don't work properly. This is due to Kotlin's null safety.
 * A common way to address this issue is by using this library:
 * https://github.com/nhaarman/mockito-kotlin
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
        client = WebTestClient.bindToController(controller)
                .configureClient()
                .baseUrl("/")
                .build()
    }

    @Test
    fun `test get greetings`() {
        Mockito.`when`(helloService.getHello()).thenReturn(Flux.just(Hello("1", "tets")))

        client
                .get()
                .exchange()
                .expectStatus()
                .isOk
    }

    @Test
    fun `test post greetings`() {
        val hello = Hello("1", "test")

        Mockito.`when`(helloService.saveHello(any())).thenReturn(Flux.just(hello))

        client
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(hello))
                .exchange()
                .expectStatus()
                .isOk
                .expectBody()
                .json("""
                    | [{
                    |   "id": "1",
                    |   "greeting": "test"
                    | }]
                """.trimMargin())
    }
}