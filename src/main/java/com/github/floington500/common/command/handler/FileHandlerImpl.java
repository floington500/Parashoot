package com.github.floington500.common.command.handler;

import com.github.floington500.common.command.Operation;
import com.github.floington500.common.command.context.FileContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Handles the uploading and downloading of files.
 */
@Service
public class FileHandlerImpl implements FileHandler {

    private final List<Operation> fileOperations;

    public FileHandlerImpl(List<Operation> fileOperations) {
        this.fileOperations = fileOperations;
    }

    public Optional<Operation> getOperation(String name) {
        return fileOperations.stream()
                .filter(operation -> operation.getName().equals(name))
                .findFirst();
    }

    public ResponseEntity<Object> performFileOperation(String operation, FileContext ctx) {
        Optional<Operation> fileOperation = getOperation(operation);
        if (fileOperation.isEmpty()) {
            return ResponseEntity.badRequest().body("Unsupported operation.");
        }
        return fileOperation.get().handle(ctx);
    }
}
