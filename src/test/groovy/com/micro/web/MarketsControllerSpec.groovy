package com.micro.web

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.rxjava3.http.client.Rx3HttpClient
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import static org.assertj.core.api.Assertions.*
import spock.lang.Specification

@MicronautTest
class MarketsControllerSpec extends Specification {
    @Inject
    EmbeddedApplication application

//    @Shared @Inject
//    EmbeddedServer embeddedServer
//
//    @Shared @AutoCleanup @Inject @Client("/")
//    HttpClient client

    @Inject
    @Client("/") Rx3HttpClient client

    void "returnsListOfMarkets"(){
        when:
        final List result = client.toBlocking().retrieve("/markets", List.class)
        then:
        result.size() == 7
//        assertThat(result).containsExactlyInAnyOrder("AAPL", "AMZN", "FB", "GOOG", "MSFT", "NFLX", "TSLA")
        result.collect{it.get("value")}.containsAll("AAPL", "AMZN", "FB", "GOOG", "MSFT", "NFLX", "TSLA")
    }
}