package com.micro.web.broker.store;

import com.micro.web.broker.model.*;
import jakarta.inject.*;

import java.util.*;
import java.util.stream.*;

@Singleton
public class InMemoryStore {
    private final List<Symbol> symbols;

    public InMemoryStore() {
        this.symbols = Stream.of("AAPL", "AMZN", "FB", "GOOG", "MSFT", "NFLX", "TSLA" )
                .map(Symbol::new)
                .collect(Collectors.toList());
    }


    public List<Symbol> getAllSymbols() {
        return symbols;
    }
}