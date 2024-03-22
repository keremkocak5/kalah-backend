package com.kocak.kalah.exception;

import com.kocak.kalah.model.enums.KalahError;
import lombok.Getter;

public class KalahRuntimeException extends RuntimeException {

    @Getter
    private final KalahError kalahError;

    public KalahRuntimeException(KalahError kalahError) {
        this.kalahError = kalahError;
    }
}
