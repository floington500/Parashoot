package com.github.floington500.controller.handler.strategy;

import com.github.floington500.controller.download.DownloadHeaders;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Creates the body of the response by opening
 * the resource requested by the client and
 * putting it in a response entity that can
 * be rendered in the browser.
 */
@Component
public class DownloadFile extends FileAction {

    @Override
    protected ResponseEntity<Object> handleFile(MultipartFile payload) {
        File file = new File(filename);

        InputStreamResource resource;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        String contentType;
        try {
            contentType = Files.probeContentType(file.toPath());
        } catch (IOException e) {
            contentType = "plain/txt"; // render the file in plain text if a format cannot be identified
        }

        return ResponseEntity.ok().headers(new DownloadHeaders(filename))
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @Override
    public String getName() {
        return "download";
    }
}
