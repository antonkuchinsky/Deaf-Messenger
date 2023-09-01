package com.who.messageservice.exception;

import lombok.Getter;

@Getter
public class InvalidDataException extends RuntimeException {
    private final String title;

    public InvalidDataException(String message, String title) {
        super(message);
        this.title = title;
    }

}
