package com.micro.web.broker;

import com.micro.web.broker.model.*;
import com.micro.web.broker.store.*;
import io.micronaut.http.*;
import io.micronaut.http.annotation.*;

@Controller("/quotes")
public class QuotesController {
    private final InMemoryStore store;

    public QuotesController(InMemoryStore store) {
        this.store = store;
    }

    @Get("/{symbol}")
    public HttpResponse getQuote(@PathVariable String symbol){
        Quote quote = store.fetchQuote(symbol);
        return HttpResponse.ok(quote);
    }
}