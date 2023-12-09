package com.github.floington500.controller.service.strategy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IOperation {
    // TODO: implement context to use instead
    ResponseEntity<Object> handle(MultipartFile payload, String URI);
}
