package com.github.floington500.controller.handler.strategy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface Handler {
    // TODO: implement context to use instead
    ResponseEntity<Object> handle(MultipartFile payload, String URI);
    String getName();
}
