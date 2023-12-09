package com.github.floington500.controller.handler.context;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public record FileContext(MultipartFile payload, String URI) {
}
