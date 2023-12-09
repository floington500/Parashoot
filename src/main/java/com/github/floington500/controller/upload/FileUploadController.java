package com.github.floington500.controller.upload;

import com.github.floington500.controller.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileService fileService;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("OK");
    }

    /**
     * Endpoint for handling requests from the client to upload files.
     *
     * @param file the file uploaded by the client
     * @param request used to map the file the client wants to the local filesystem.
     * @return if the upload was successful
     */
    @PostMapping("**")
    public ResponseEntity<Object> uploadFile(
            @RequestParam("file") MultipartFile payload,
            HttpServletRequest request
    ) {
        return fileService.performFileOperation("upload", payload, request.getRequestURI());
    }
}
