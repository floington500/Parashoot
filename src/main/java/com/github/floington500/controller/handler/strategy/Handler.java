package com.github.floington500.controller.handler.strategy;

import com.github.floington500.controller.handler.context.FileContext;
import org.springframework.http.ResponseEntity;

public interface Handler {
    ResponseEntity<Object> handle(FileContext ctx);
    String getName();
}
