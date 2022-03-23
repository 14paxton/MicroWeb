package com.micro.web

import com.micro.web.broker.model.Quote
import com.micro.web.broker.store.com.micro.web.broker.error.CustomError
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.rxjava3.http.client.Rx3HttpClient
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class QuotesControllerSpec extends Specification {
    @Inject
    EmbeddedApplication application

//    @Shared @Inject
//    EmbeddedServer embeddedServeradsfads--
//
//    @Shared @AutoCleanup @Inject @Client("/")
//    HttpClient client

    @Inject
    @Client("/") Rx3HttpClient client


    void "thrownException"(){
        when:
        HttpClientResponseException errorResponse
        try{
            client.toBlocking().retrieve(HttpRequest.GET("/quotes/EXCEPTION"), Argument.of(Quote.class) as Argument<Object>, Argument.of(CustomError.class))
        } catch(HttpClientResponseException e){
            errorResponse = e
        }

        then:
        HttpStatus.NOT_FOUND == errorResponse?.getResponse()?.getStatus()
        Optional<CustomError> ce  = errorResponse.getResponse().getBody(CustomError.class)
        CustomError error = ce.get()
        404 == error.status
        "NOT_FOUND" == error.error
        "quote for symbol not available" == error.message
        "/quote/EXCEPTION" == error.path
    }

    void "returnQuotePerSymbol"(){
        when:
        Quote appleResult = client.toBlocking().retrieve(HttpRequest.GET("/quotes/AAPL"), Quote.class)
        then:
        appleResult
    }
}