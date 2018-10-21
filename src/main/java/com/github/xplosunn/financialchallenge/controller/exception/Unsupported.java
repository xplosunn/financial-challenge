package com.github.xplosunn.financialchallenge.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public final class Unsupported {

    private Unsupported() {}

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Submitted number of days unsupported (max is 31)")
    public static class NumberOfDays extends RuntimeException {}
}
