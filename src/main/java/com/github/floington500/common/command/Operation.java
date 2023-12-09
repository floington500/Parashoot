package com.github.floington500.common.command;

import com.github.floington500.common.command.context.FileContext;
import com.github.floington500.common.command.handler.FileHandler;
import org.springframework.http.ResponseEntity;

/**
 * Specifies a set of operations required to be
 * used by the {@link FileHandler}
 */
public interface Operation {
    ResponseEntity<Object> handle(FileContext ctx);
    String getName();
}
