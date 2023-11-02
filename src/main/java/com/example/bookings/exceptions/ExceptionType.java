package com.example.bookings.exceptions;

public enum ExceptionType {
    ENTITY_NOT_FOUND("not.found"),
    DUPLICATE_ENTITY("duplicate"),
    NOT_AVAILABLE("not.available"),
    FORBIDDEN("forbidden");

    final String value;

    ExceptionType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}