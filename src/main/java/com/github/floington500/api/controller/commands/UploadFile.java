package com.github.floington500.api.controller.commands;

import com.github.floington500.common.command.FileOperation;
import com.github.floington500.common.context.FileContext;
import com.github.floington500.common.exceptions.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Handles files uploaded by the client
 * and stores them to the local filesystem.
 */
@Component
public class UploadFile extends FileOperation {

    @Override
    protected ResponseEntity<Object> handleFile(FileContext ctx) {
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (!created) {
                    throw new FileUploadException("Failed to create directory.", HttpStatus.BAD_REQUEST);
                }
            }

            File file = new File(directoryPath + "/" + filename);
            if (file.exists()) {
                throw new FileUploadException("File already exists.", HttpStatus.CONFLICT);
            }

            ctx.payload().transferTo(file); // create file
            return ResponseEntity.ok("File successfully uploaded.");

        } catch (FileUploadException e) {
            return e.buildResponseEntity();

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();

        }
    }

    @Override
    public String getName() {
        return "upload";
    }
}
