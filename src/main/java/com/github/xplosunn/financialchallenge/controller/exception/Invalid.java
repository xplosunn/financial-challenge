package com.github.xplosunn.financialchallenge.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public final class Invalid {

    private Invalid() {}

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Submitted number of days is invalid")
    public static class NumberOfDays extends RuntimeException {}
}
