package com.github.xplosunn.financialchallenge.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public final class NotFound {

    private NotFound() {}

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="User not found")
    public static class User extends RuntimeException {}
}
