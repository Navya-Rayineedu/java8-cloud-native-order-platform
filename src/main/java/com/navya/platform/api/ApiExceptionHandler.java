package com.navya.platform.api;

import com.navya.platform.order.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, Object> notFound(OrderNotFoundException ex) {
        return Map.of("timestamp", Instant.now(), "error", ex.getMessage());
    }
}
