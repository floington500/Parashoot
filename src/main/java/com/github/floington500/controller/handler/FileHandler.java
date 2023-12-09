package com.github.floington500.controller.handler;

import com.github.floington500.controller.handler.strategy.Handler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FileHandler {
    Optional<Handler> getOperation(String name);
    ResponseEntity<Object> performFileOperation(String operation, MultipartFile payload, String URI);
}
