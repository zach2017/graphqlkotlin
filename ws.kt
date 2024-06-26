import okhttp3.*
import okio.ByteString
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit


import okhttp3.*
import okio.ByteString
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

class WebSocketServerTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var webSocketClient: WebSocket

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url(mockWebServer.url("/ws"))
            .build()

        webSocketClient = client.newWebSocket(request, object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                // Handle incoming text messages
                println("Received text message: $text")
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                // Handle incoming binary messages
                println("Received binary message: ${bytes.hex()}")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                // Handle WebSocket failure
                t.printStackTrace()
            }
        })
    }

    @AfterEach
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testWebSocketTextMessage() {
        val expectedMessage = "Hello, WebSocket!"
dependencies {
    testImplementation("io.projectreactor:reactor-test:3.4.6")
}

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

@SpringBootTest
class FluxPublisherTest {

    @Autowired
    private lateinit var fluxPublisher: FluxPublisher

    @Test
    fun testFluxPublisher() {
        // Create a sample Flux publisher
        val flux: Flux<String> = fluxPublisher.publish()

        // Use StepVerifier to verify the Flux publisher
        StepVerifier.create(flux)
            .expectNext("Item 1")
            .expectNext("Item 2")
            .expectNext("Item 3")
            .expectComplete()
            .verify()
    }
}

class FluxPublisher {
    fun publish(): Flux<String> {
        return Flux.just("Item 1", "Item 2", "Item 3")
    }
}
        // Enqueue a mock response from the server
        mockWebServer.enqueue(MockResponse().withWebSocketUpgrade(object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {

                
                // Send a text message when the WebSocket connection is opened
                webSocket.send(expectedMessage)
            }
        }))

        // Verify that the client receives the expected message
        // You can use assertions or checks here based on your testing framework
        // For example:
        // assertEquals(expectedMessage, receivedMessage)
    }

    @Test
    fun testWebSocketBinaryMessage() {
        val expectedBinaryMessage = ByteString.decodeHex("deadbeef")

        // Enqueue a mock response from the server
        mockWebServer.enqueue(MockResponse().withWebSocketUpgrade(object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                // Send a binary message when the WebSocket connection is opened
                webSocket.send(expectedBinaryMessage)
            }
        }))

        // Verify that the client receives the expected binary message
        // You can use assertions or checks here based on your testing framework
        // For example:
        // assertEquals(expectedBinaryMessage, receivedBinaryMessage)
    }
}
class WebSocketServerTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var webSocketClient: WebSocket

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url(mockWebServer.url("/ws"))
            .build()

        webSocketClient = client.newWebSocket(request, object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                // Handle incoming text messages
                println("Received text message: $text")
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                // Handle incoming binary messages
                println("Received binary message: ${bytes.hex()}")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                // Handle WebSocket failure
                t.printStackTrace()
            }
        })
    }

    @AfterEach
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testWebSocketTextMessage() {
        val expectedMessage = "Hello, WebSocket!"

        // Enqueue a mock response from the server
        mockWebServer.enqueue(MockResponse().withWebSocketUpgrade(object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                // Send a text message when the WebSocket connection is opened
                webSocket.send(expectedMessage)
            }
        }))

        // Verify that the client receives the expected message
        // You can use assertions or checks here based on your testing framework
        // For example:
        // assertEquals(expectedMessage, receivedMessage)
    }

    @Test
    fun testWebSocketBinaryMessage() {
        val expectedBinaryMessage = ByteString.decodeHex("deadbeef")

        // Enqueue a mock response from the server
        mockWebServer.enqueue(MockResponse().withWebSocketUpgrade(object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                // Send a binary message when the WebSocket connection is opened
                webSocket.send(expectedBinaryMessage)
            }
        }))

        // Verify that the client receives the expected binary message
        // You can use assertions or checks here based on your testing framework
        // For example:
        // assertEquals(expectedBinaryMessage, receivedBinaryMessage)
    }
}
