package com.github.floington500.api.controller.upload;

import com.github.floington500.common.command.context.FileContextFactory;
import com.github.floington500.common.command.handler.FileHandlerImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileHandlerImpl fileService;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("OK");
    }

    /**
     * Saves a file to the local filesystem.
     *
     * @param payload the file to save
     * @param request contains the path for the file in the local filesystem
     * @return 200 - success
     */
    @PostMapping("**")
    public ResponseEntity<Object> uploadFile(
            @RequestParam("file") MultipartFile payload,
            HttpServletRequest request
    ) {
        return fileService.performFileOperation("upload", FileContextFactory.createContext(request, payload));
    }

    /**
     * Replaces an existing file.
     *
     * @param payload the new file
     * @param request contains the path for the file in the local filesystem
     * @return 200 - success
     */
    @PutMapping("/**")
    public ResponseEntity<Object> updateFile(
            @RequestParam("file") MultipartFile payload,
            HttpServletRequest request
    ) {
        return fileService.performFileOperation("update", FileContextFactory.createContext(request, payload));
    }
}
