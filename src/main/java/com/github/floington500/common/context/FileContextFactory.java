package com.github.floington500.common.context;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * Creates context objects to decouple the number of parameters used in
 * controllers.
 */
public class FileContextFactory {

    public static FileContext createContext(HttpServletRequest request) {
        return new FileContext(null, request.getRequestURI());
    }

    public static FileContext createContext(MultipartFile payload) {
        return new FileContext(payload, null);
    }

    public static FileContext createContext(HttpServletRequest request, MultipartFile payload) {
        return new FileContext(payload, request.getRequestURI());
    }
}
