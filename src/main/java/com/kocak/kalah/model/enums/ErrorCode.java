package com.kocak.kalah.model.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    NO_SUCH_GAME_FOUND("E0000", "This game does not exist.", HttpStatus.UNPROCESSABLE_ENTITY),
    GAME_NOT_ACTIVE("E0001", "This game is already over.", HttpStatus.UNPROCESSABLE_ENTITY),
    NO_SUCH_PIT_FOUND("E0002", "This pit does not exist in this board.", HttpStatus.UNPROCESSABLE_ENTITY),
    NOT_PLAYERS_TURN("E0003", "This is another player's turn.", HttpStatus.UNPROCESSABLE_ENTITY),
    TOKEN_COUNT_ZERO("E0004", "This pit has no tokens.", HttpStatus.UNPROCESSABLE_ENTITY),
    CANNOT_PLAY_KALAH("E0005", "This pit is a Kalah.", HttpStatus.UNPROCESSABLE_ENTITY),
    UNKNOWN_RULE("E0006", "There has been a internal game rule issue.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNKNOWN_ERROR("E9999", "Unknown error, please contact the development team.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String errorId;
    private final String errorMessage;
    private final HttpStatusCode httpStatus;

    ErrorCode(String errorId, String errorMessage, HttpStatus httpStatus) {
        this.errorId = errorId;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
