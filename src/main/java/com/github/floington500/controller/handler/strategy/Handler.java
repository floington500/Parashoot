package com.github.floington500.controller.handler.strategy;

import com.github.floington500.controller.handler.context.FileContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface Handler {
    ResponseEntity<Object> handle(FileContext ctx);
    String getName();
}
