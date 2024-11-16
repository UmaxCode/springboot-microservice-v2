package com.umaxcode.microservices.order.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ErrorResponse {

    private String path;
    private String message;
    private LocalDateTime timestamp;
}
