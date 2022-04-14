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
    private final Map<String, Quote> cachedQuotes = new HashMap<>();

    public InMemoryStore() {
        this.symbols = Stream.of("AAPL", "AMZN", "FB", "GOOG", "MSFT", "NFLX", "TSLA")
                .map(Symbol::new)
                .collect(Collectors.toList());
        this.symbols.forEach(symbol -> {
            cachedQuotes.put(symbol.getValue(), randomQuote(symbol));
        });
    }

    private Quote randomQuote(Symbol symbol) {
        return Quote.builder()
                .symbol(symbol)
                .bid(randomValue())
                .ask(randomValue())
                .lastPrice(randomValue())
                .build();
    }

    public List<Symbol> getAllSymbols() {
        return symbols;
    }

    public Optional<Quote> fetchQuote(final String symbol) {
        return Optional.ofNullable(cachedQuotes.get(symbol));
    }

    private BigDecimal randomValue() {
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1, 100));
    }

    public void update(final Quote quote) {
        cachedQuotes.put(quote.getSymbol().getValue(), quote);
    }
}