package com.github.floington500.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@Getter
public class FileUploadException extends IOException {

    private final HttpStatus responseCode;

    public FileUploadException(String message, HttpStatus responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public ResponseEntity<String> buildResponseEntity() {
        return ResponseEntity.status(responseCode).body(getMessage());
    }
}
