package com.github.floington500.common.context;

import org.springframework.web.multipart.MultipartFile;

/**
 * Contains relevant information relating to a request sent
 * by the user containing a file.
 * @param payload the data for the file
 * @param URI the URL that the user submitted
 */
public record FileContext(MultipartFile payload, String URI) {
}
