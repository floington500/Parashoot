package com.github.floington500.api.controller.commands;

import com.github.floington500.common.command.FileOperation;
import com.github.floington500.common.command.context.FileContext;
import com.github.floington500.common.exceptions.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Replaces an already existing file with one
 * provided by the client.
 */
@Component
public class UpdateFile extends FileOperation {

    @Override
    protected ResponseEntity<Object> handleFile(FileContext ctx) {
        File file = new File(filename);

        try {
            if (!file.exists()) {
                throw new FileUploadException("File could not be found.", HttpStatus.NOT_FOUND);
            }

            ctx.payload().transferTo(file);
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
