package com.kocak.kalah.exception;

import lombok.Getter;

public class KalahValidationException extends RuntimeException {

    @Getter
    private final String validationMessage;

    public KalahValidationException(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}
