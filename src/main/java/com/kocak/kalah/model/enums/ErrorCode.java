package com.kocak.kalah.model.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    GAME_NOT_ACTIVE("E0001", "This game is already over.", HttpStatus.UNPROCESSABLE_ENTITY),
    NOT_USERS_TURN("E0002", "This is another users turn.", HttpStatus.UNPROCESSABLE_ENTITY),
    PIT_COUNT_ZERO("E0003", "This pit has no tokens.", HttpStatus.UNPROCESSABLE_ENTITY),
    NOT_USERS_PIT("E0004", "This pit belongs to another player.", HttpStatus.UNPROCESSABLE_ENTITY);

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    ErrorCode(String errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
