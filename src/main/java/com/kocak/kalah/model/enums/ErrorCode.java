package com.kocak.kalah.model.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    NO_SUCH_GAME_FOUND("E0000", "This game does now exist.", HttpStatus.UNPROCESSABLE_ENTITY),
    GAME_NOT_ACTIVE("E0001", "This game is already over.", HttpStatus.UNPROCESSABLE_ENTITY),
    NO_SUCH_PIT_FOUND("E0003", "This pit does not exist in this board.", HttpStatus.UNPROCESSABLE_ENTITY),
    NOT_USERS_TURN("E0004", "This is another users turn.", HttpStatus.UNPROCESSABLE_ENTITY),
    TOKEN_COUNT_ZERO("E0005", "This pit has no tokens.", HttpStatus.UNPROCESSABLE_ENTITY);

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    ErrorCode(String errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
