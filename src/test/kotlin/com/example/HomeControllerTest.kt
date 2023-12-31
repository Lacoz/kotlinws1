package com.example;
import com.amazonaws.services.lambda.runtime.*

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import io.micronaut.function.aws.proxy.payload1.ApiGatewayProxyRequestEventFunction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import io.micronaut.http.HttpMethod

class FunctionRequestHandlerTest {

    @Test
    fun testFunctionRequestHandler() {
        val handler = ApiGatewayProxyRequestEventFunction()
        val request = APIGatewayProxyRequestEvent()
        request.httpMethod = "GET"
        request.path = "/"
        val response = handler.handleRequest(request, lambdaContext)
        assertEquals(200, response.statusCode)
        assertEquals("{\"message\":\"Hello World\"}", response.body)
        handler.applicationContext.close()
    }

    val lambdaContext = object : Context {
        override fun getAwsRequestId(): String {
            return ""
        }

        override fun getLogGroupName(): String {
            return ""
        }

        override fun getLogStreamName(): String {
            return ""
        }

        override fun getFunctionName(): String {
            return ""
        }

        override fun getFunctionVersion(): String {
            return ""
        }

        override fun getInvokedFunctionArn(): String {
            return ""
        }

        override fun getIdentity(): CognitoIdentity {
            return object : CognitoIdentity {
                override fun getIdentityId(): String {
                    return ""
                }

                override fun getIdentityPoolId(): String {
                    return ""
                }
            }
        }

        override fun getClientContext(): ClientContext {
            return object : ClientContext {
                override fun getClient(): Client {
                    TODO("Not yet implemented")
                }

                override fun getCustom(): MutableMap<String, String> {
                    return mutableMapOf()
                }

                override fun getEnvironment(): MutableMap<String, String> {
                    return mutableMapOf()
                }
            }
        }

        override fun getRemainingTimeInMillis(): Int {
            return 0
        }

        override fun getMemoryLimitInMB(): Int {
            return 0
        }

        override fun getLogger(): LambdaLogger {
            return object : LambdaLogger {
                override fun log(message: String?) {
                }
                override fun log(message: ByteArray?) {
                }
            }
        }

    }
}

