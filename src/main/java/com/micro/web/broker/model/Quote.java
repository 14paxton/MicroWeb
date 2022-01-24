package com.micro.web.broker.model;

import lombok.*;

import java.math.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
    private Symbol symbol;
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal lastPrice;
    private BigDecimal volume;
}