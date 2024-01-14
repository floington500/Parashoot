package com.github.floington500.common.command;

import com.github.floington500.common.command.context.FileContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Provides a base implementation for handlers.
 * <p>
 * This could be useful in the future if I need
 * to sanitize input or something similar.
 */
public abstract class FileOperation implements Operation {

    /**
     *
     * Prefixed for paths provided by the request to map them to the local filesystem path.
     */
    @Value("${server.local.storage}")
    private String PATH;

    protected String filename;
    protected String directoryPath;

    @Override
    public ResponseEntity<Object> handle(FileContext ctx) {
        if (ctx.payload().isEmpty()) {
            filename = extractFilenameFromURI(ctx.URI());
        } else {
            filename = ctx.payload().getOriginalFilename();
        }

        directoryPath = constructDirectoryPath(ctx.URI());

        return handleFile(ctx);
    }

    /**
     * Extracts the filename from a path.
     */
    private String extractFilenameFromURI(String path) {
        return Paths.get(path).getFileName().toString();
    }

    /**
     * Maps external paths from the request to one for
     * the local filesystem.
     *
     * @param path the external path from the request
     * @return a path that can be used for the local filesystem
     */
    private String constructDirectoryPath(String path) {
        Path varPath = Paths.get(PATH);
        if (path.indexOf("files") != -1) {
            Path relativePath = Paths.get(path.substring(7));
            return varPath.resolve(relativePath).getParent().toString();
        }
        return varPath.resolve(path.substring(8)).toString();
    }

    protected abstract ResponseEntity<Object> handleFile(FileContext ctx);
}
