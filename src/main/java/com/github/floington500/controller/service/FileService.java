package com.github.floington500.controller.service;

import com.github.floington500.controller.service.strategy.DeleteFile;
import com.github.floington500.controller.service.strategy.DownloadFile;
import com.github.floington500.controller.service.strategy.IOperation;
import com.github.floington500.controller.service.strategy.UpdateFile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles the uploading and downloading of files.
 */
@Service
public class FileService {

    private final Map<String, IOperation> fileOperations = new HashMap<>();

    public FileService() {
        fileOperations.put("download", new DownloadFile());
        fileOperations.put("delete", new DeleteFile());
        fileOperations.put("update", new UpdateFile());
        fileOperations.put("upload", new UpdateFile());
    }

    public ResponseEntity<Object> performFileOperation(String operation, MultipartFile payload, String URI) {
        IOperation fileOperation = fileOperations.get(operation);
        if (fileOperation == null) {
            return ResponseEntity.badRequest().body("Unsupported operation.");
        }
        return fileOperation.handle(payload, URI);
    }
}
