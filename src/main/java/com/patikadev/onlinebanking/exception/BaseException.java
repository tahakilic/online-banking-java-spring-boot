package com.patikadev.onlinebanking.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseException extends RuntimeException{
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
