package com.github.floington500.controller.handler;

import com.github.floington500.controller.handler.context.FileContext;
import com.github.floington500.controller.handler.strategy.Handler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Handles the uploading and downloading of files.
 */
@Service
public class FileHandlerImpl implements FileHandler {

    private final List<Handler> fileOperations;

    public FileHandlerImpl(List<Handler> fileOperations) {
        this.fileOperations = fileOperations;
    }

    public Optional<Handler> getOperation(String name) {
        for (Handler operation : fileOperations) {
            if (operation.getName().equals(name)) {
                return Optional.of(operation);
            }
        }

        return Optional.empty();
    }

    public ResponseEntity<Object> performFileOperation(String operation, FileContext ctx) {
        Optional<Handler> fileOperation = getOperation(operation);
        if (fileOperation.isEmpty()) {
            return ResponseEntity.badRequest().body("Unsupported operation.");
        }
        return fileOperation.get().handle(ctx);
    }
}
