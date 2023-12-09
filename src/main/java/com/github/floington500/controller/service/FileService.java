package com.github.floington500.controller.service;

import com.github.floington500.controller.download.DownloadHeaders;
import com.github.floington500.exceptions.FileUploadException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Handles the uploading and downloading of files.
 */
@Service
public class FileService {

    /**
     * Prefixed for paths provided by the request to map them to the local filesystem path.
     */
    private static final String VAR = "/var";

    /**
     * Creates the body of the response by opening the resource requested by the client and putting it in a response
     * entity that can be rendered in the browser.
     *
     * @param path the resource identifier that the client wants to retrieve
     * @return a response containing the file the user requested, or not found if it does not exist
     */
    public ResponseEntity<Object> createBody(String path) {
        String filename = toLocalPath("/upload") + pathToFilename(path);
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

    /**
     * Extracts the filename from a path.
     */
    private String pathToFilename(String path) {
        // TODO: handle case of user querying root
        return path.split("/files")[1];
    }

    /**
     * Handles uploaded files from the client by storing them to the local filesystem.
     *
     * @param payload the file uploaded by the client
     * @param URI where to upload the file to
     * @return a response containing if the upload was successful or not
     */
    public ResponseEntity<String> uploadFile(MultipartFile payload, String URI) {
        String filename = payload.getOriginalFilename();
        String directoryPath = toLocalPath(URI);
        String path = directoryPath + "/" + filename;

        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (!created) {
                    throw new FileUploadException("Failed to create directory.", HttpStatus.BAD_REQUEST);
                }
            }

            File file = new File(path);
            if (file.exists()) {
                throw new FileUploadException("File already exists.", HttpStatus.CONFLICT);
            }

            payload.transferTo(new File(path));
            return ResponseEntity.ok("File successfully uploaded.");

        } catch (FileUploadException e) {
            return e.buildResponseEntity();

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();

        }
    }

    /**
     * Maps external paths from the request to one for the local filesystem.
     *
     * @param path the external path from the request
     * @return a path that can be used for the local filesystem
     */
    private String toLocalPath(String path) {
        return VAR + path;
    }
}
