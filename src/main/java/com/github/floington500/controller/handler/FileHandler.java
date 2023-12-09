package com.github.floington500.controller.handler;

import com.github.floington500.controller.handler.context.FileContext;
import com.github.floington500.controller.handler.strategy.Handler;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * Provides an interface for
 * handling file operations.
 */
public interface FileHandler {
    Optional<Handler> getOperation(String name);
    ResponseEntity<Object> performFileOperation(String operation, FileContext ctx);
}
