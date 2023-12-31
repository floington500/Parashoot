package com.github.floington500.common.command;

import com.github.floington500.common.command.context.FileContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Provides a base implementation for handlers.
 * <p>
 * This could be useful in the future if I need
 * to sanitize input or something similar.
 */
public abstract class FileOperation implements Operation {

    /**
     * Prefixed for paths provided by the request to map them to the local filesystem path.
     */
    private static final String VAR = "/var";
    protected String filename;
    protected String directoryPath;

    @Override
    public ResponseEntity<Object> handle(FileContext ctx) {
        filename = toLocalPath("/upload") + pathToFilename(ctx.URI());
        directoryPath = toLocalPath(ctx.URI());

        return handleFile(ctx.payload());
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
