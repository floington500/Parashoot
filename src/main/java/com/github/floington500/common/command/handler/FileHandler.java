package com.github.floington500.common.command.handler;

import com.github.floington500.common.command.Operation;
import com.github.floington500.common.context.FileContext;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * Provides an interface for
 * handling file operations.
 */
public interface FileHandler {
    Optional<Operation> getOperation(String name);
    ResponseEntity<Object> performFileOperation(String operation, FileContext ctx);
}
