package com.micro.web.broker.store;

import com.micro.web.broker.model.*;
import jakarta.inject.*;

import java.math.*;
import java.util.*;
import java.util.concurrent.*;
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

    public Quote fetchQuote(final String symbol){
        return Quote.builder()
                .symbol(new Symbol(symbol))
                .bid(randomValue())
                .ask(randomValue())
                .lastPrice(randomValue())
                .build();
    }

    private BigDecimal randomValue() {
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1,100));
    }
}