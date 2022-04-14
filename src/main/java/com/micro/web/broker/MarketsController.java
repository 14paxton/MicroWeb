package com.micro.web.broker;

import com.micro.web.broker.model.*;
import com.micro.web.broker.store.*;
import io.micronaut.http.annotation.*;

import java.util.*;

@Controller("/markets")
public class MarketsController {

    private final InMemoryStore store;

    public MarketsController(final InMemoryStore store) {
        this.store = store;
    }

    @Get("/")
    public List<Symbol> all() {
        return store.getAllSymbols();
    }
}