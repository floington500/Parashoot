package com.github.floington500.controller.download;

import com.github.floington500.controller.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileDownloadController {

    private final FileService fileService;

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
        return fileService.createBody(request.getRequestURI());
    }

    @DeleteMapping("/**")
    public ResponseEntity<String> deleteFile(
            HttpServletRequest request
    ) {
        return fileService.deleteFile(request.getRequestURI());
    }
}
