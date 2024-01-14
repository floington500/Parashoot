package com.github.floington500.api.controller.commands;

import com.github.floington500.common.command.FileOperation;
import com.github.floington500.common.command.context.FileContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Deletes a file under the endpoint that the
 * user requested.
 */
@Component
public class DeleteFile extends FileOperation {
    @Override
    protected ResponseEntity<Object> handleFile(FileContext ctx) {
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
