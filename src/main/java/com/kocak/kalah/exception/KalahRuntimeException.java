package com.kocak.kalah.exception;

import com.kocak.kalah.model.enums.ErrorCode;
import lombok.Getter;

public class KalahRuntimeException extends RuntimeException {

    @Getter
    private final ErrorCode errorCode;

    public KalahRuntimeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
