package com.micro.web.broker;

import com.micro.web.broker.model.*;
import com.micro.web.broker.store.*;
import com.micro.web.broker.store.com.micro.web.broker.error.*;
import io.micronaut.http.*;
import io.micronaut.http.annotation.*;

import java.util.*;

@Controller("/quotes")
public class QuotesController {
    private final InMemoryStore store;

    public QuotesController(InMemoryStore store) {
        this.store = store;
    }

    @Get("/{symbol}")
    public HttpResponse getQuote(@PathVariable String symbol){
        Optional<Quote> maybeQuote = store.fetchQuote(symbol);
        if(maybeQuote.isEmpty()){
            final CustomError ce = CustomError.builder()
                    .status(HttpStatus.NOT_FOUND.getCode())
                    .error(HttpStatus.NOT_FOUND.name())
                    .message("quote for symbol not available")
                    .path("/quote/" + symbol)
                    .build();

            return HttpResponse.notFound(ce);
        }
        return HttpResponse.ok(maybeQuote.get());
    }
}