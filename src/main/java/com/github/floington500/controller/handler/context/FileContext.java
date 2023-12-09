package com.github.floington500.controller.handler.context;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class FileContext {

    private final MultipartFile payload;
    private final String URI;

    public FileContext(MultipartFile payload, String URI) {
        this.payload = payload;
        this.URI = URI;
    }
}
