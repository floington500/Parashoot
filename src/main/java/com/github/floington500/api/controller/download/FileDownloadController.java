package com.github.floington500.api.controller.download;

import com.github.floington500.common.command.context.FileContextFactory;
import com.github.floington500.common.command.handler.FileHandlerImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileDownloadController {

    private final FileHandlerImpl fileService;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("OK");
    }

    /**
     * Provides access to the uploaded files.
     *
     * @param request used to map the file the client wants to the local filesystem.
     * @return the file requested by the client
     */
    @GetMapping("/**")
    public ResponseEntity<Object> downloadFile(
            HttpServletRequest request
    ) {
        return fileService.performFileOperation("download", FileContextFactory.createContext(request));
    }

    @DeleteMapping("/**")
    public ResponseEntity<Object> deleteFile(
            HttpServletRequest request
    ) {
        return fileService.performFileOperation("delete", FileContextFactory.createContext(request));
    }

    @PutMapping("/**")
    public ResponseEntity<Object> updateFile(
            @RequestParam("file") MultipartFile payload,
            HttpServletRequest request
    ) {
        return fileService.performFileOperation("update", FileContextFactory.createContext(request, payload));
    }
}
