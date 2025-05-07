package com.ilyin.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class<?> clazz, Object id) {
        super(clazz.getSimpleName() + " with ID " + id + " not found");
    }
}
