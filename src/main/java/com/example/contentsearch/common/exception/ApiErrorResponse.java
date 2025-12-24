package com.example.contentsearch.common.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiErrorResponse {

    private final String errorCode;
    private final String message;
    private final String path;
    private final LocalDateTime timestamp;
}

