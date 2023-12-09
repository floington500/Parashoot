package com.github.floington500.api.controller.download;

import org.springframework.http.HttpHeaders;

/**
 * Stores the required headers for downloading a file.
 */
public class DownloadHeaders extends HttpHeaders {

    public DownloadHeaders(String filename) {
        add("Content-Disposition", String.format("inline; filename=\"%s\"", filename));
        add("Cache-Control", "no-cache, no-store, must-revalidate");
        add("Pragma", "no-cache");
        add("Expires", "0");
    }
}
