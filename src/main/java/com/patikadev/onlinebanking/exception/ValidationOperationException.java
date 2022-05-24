package com.patikadev.onlinebanking.exception;

public final class ValidationOperationException {
    public ValidationOperationException() {
    }

    public static class IDNotValidException extends BaseException {
        public IDNotValidException(String message) {
            super(message);
        }
    }

    public static class CustomerNotValidException extends BaseException {
        public CustomerNotValidException(String message) {
            super(message);
        }
    }
}
