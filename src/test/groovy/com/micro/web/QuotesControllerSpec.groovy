package com.micro.web

import com.micro.web.broker.model.Quote
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.annotation.Client
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

    void "returnQuotePerSymbol"(){
        when:
        Quote appleResult = client.toBlocking().retrieve(HttpRequest.GET("/quotes/APPL"), Quote.class)
        then:
        appleResult
    }
}