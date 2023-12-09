package com.github.floington500.controller.handler.strategy;

import com.github.floington500.controller.handler.context.FileContext;
import org.springframework.http.ResponseEntity;

/**
 * Specifies a set of operations required to be
 * used by the {@link com.github.floington500.controller.handler.FileHandler}
 */
public interface Handler {
    ResponseEntity<Object> handle(FileContext ctx);
    String getName();
}
