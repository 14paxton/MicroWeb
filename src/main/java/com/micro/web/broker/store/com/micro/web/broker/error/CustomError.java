package com.micro.web.broker.store.com.micro.web.broker.error;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    private int status;
    private String error;
    private String message;
    private String path;
}