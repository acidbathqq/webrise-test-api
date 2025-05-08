package com.ilyin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionUtils {

    private static final Logger log = LoggerFactory.getLogger(ExceptionUtils.class);

    public static <E extends RuntimeException> void logAndThrow(E exception) {
        log.error(exception.getMessage(), exception);
        throw exception;
    }

}
