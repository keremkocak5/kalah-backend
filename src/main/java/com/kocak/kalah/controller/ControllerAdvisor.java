package com.kocak.kalah.controller;

import com.kocak.kalah.exception.KalahRuntimeException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {KalahRuntimeException.class})
    protected ProblemDetail handleKalahRuntimeException(KalahRuntimeException e) {
        return ProblemDetail.forStatusAndDetail(e.getErrorCode().getHttpStatus(), e.getErrorCode().getErrorMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    protected ProblemDetail handleException(Exception e) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
    }

}
