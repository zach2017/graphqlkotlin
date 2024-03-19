import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Subscription
import com.apollographql.apollo.api.toJson
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import okhttp3.OkHttpClient
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class GraphQLSubscriptionTest {

    @ExperimentalCoroutinesApi
    @Test
    fun testGraphQLSubscription() = runBlocking {
        val countDownLatch = CountDownLatch(1)
        var receivedData: String? = null

        val okHttpClient = OkHttpClient.Builder().build()
        val apolloClient = ApolloClient.builder()
            .serverUrl("ws://localhost:8080/graphql")
            .subscriptionTransportFactory(WebSocketSubscriptionTransport.Factory(okHttpClient))
            .build()

        val subscription = object : Subscription<Operation.Data, Operation.Data, Operation.Variables> {
            override fun queryDocument(): String = "subscription { messageReceived { message } }"
            override fun wrapData(data: Operation.Data?): Operation.Data = data!!
            override fun variables(): Operation.Variables = object : Operation.Variables {}
            override fun name(): Operation<*, *, *> = this
        }

        apolloClient.subscribe(subscription).execute(
            object : ApolloSubscriptionCall.Callback<Operation.Data> {
                override fun onResponse(response: Response<Operation.Data>) {
                    receivedData = response.data?.toJson()
                    countDownLatch.countDown()
                }

                override fun onFailure(e: ApolloException) {
                    e.printStackTrace()
                    countDownLatch.countDown()
                }

                override fun onCompleted() {
                    println("Subscription completed")
                }
            }
        )

        // Simulate sending a message through the GraphQL subscription
        withTimeout(5000) {
            sendMessage("Hello, World!")
        }

        // Wait for the subscription to receive the message
        countDownLatch.await(10, TimeUnit.SECONDS)

        // Verify the received data
        assert(receivedData != null)
        assert(receivedData?.contains("Hello, World!") == true)
    }

    // Helper function to simulate sending a message
    private suspend fun sendMessage(message: String) {
        delay(1000) // Simulating a delay before sending the message
        // Code to send the message through the GraphQL subscription
        // ...
    }
}
