package com.github.floington500.api.controller.download;

import com.github.floington500.common.context.FileContextFactory;
import com.github.floington500.common.command.handler.FileHandlerImpl;
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

    /**
     * Handles requests to delete a file.
     *
     * @param request contains the path for the file in the local filesystem
     * @return 200 - success
     */
    @DeleteMapping("/**")
    public ResponseEntity<Object> deleteFile(
            HttpServletRequest request
    ) {
        return fileService.performFileOperation("delete", FileContextFactory.createContext(request));
    }
}
