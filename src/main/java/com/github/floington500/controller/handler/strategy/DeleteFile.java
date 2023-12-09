package com.github.floington500.controller.handler.strategy;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public class DeleteFile extends FileAction {
    @Override
    protected ResponseEntity<Object> handleFile(MultipartFile payload) {
        File file = new File(filename);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        return file.delete() ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    @Override
    public String getName() {
        return "delete";
    }
}
