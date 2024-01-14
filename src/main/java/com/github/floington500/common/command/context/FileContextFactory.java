package com.github.floington500.common.command.context;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public class FileContextFactory {

    public static FileContext createContext(HttpServletRequest request) {
        return new FileContext(null, request.getRequestURI());
    }

    public static FileContext createContext(HttpServletRequest request, MultipartFile payload) {
        return new FileContext(payload, request.getRequestURI());
    }
}
