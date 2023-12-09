package com.github.floington500.controller.handler.strategy;

import com.github.floington500.exceptions.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class UpdateFile extends FileAction {

    @Override
    protected ResponseEntity<Object> handleFile(MultipartFile payload) {
        File file = new File(filename);

        try {
            if (!file.exists()) {
                throw new FileUploadException("File could not be found.", HttpStatus.NOT_FOUND);
            }

            payload.transferTo(file);
            return ResponseEntity.ok("OK");

        } catch (FileUploadException e) {
            return e.buildResponseEntity();

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();

        }
    }

    @Override
    public String getName() {
        return "update";
    }
}
