package com.github.floington500.controller.service.strategy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public abstract class FileAction implements IOperation {

    /**
     * Prefixed for paths provided by the request to map them to the local filesystem path.
     */
    private static final String VAR = "/var";
    protected String filename;
    protected String directoryPath;

    @Override
    public ResponseEntity<Object> handle(MultipartFile payload, String URI) {
        filename = toLocalPath("/upload") + pathToFilename(URI);
        String directoryPath = toLocalPath(URI);

        return handleFile(payload);
    }

    /**
     * Extracts the filename from a path.
     */
    private String pathToFilename(String path) {
        // TODO: handle case of user querying root
        return path.split("/files")[1];
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

    protected abstract ResponseEntity<Object> handleFile(MultipartFile payload);
}
