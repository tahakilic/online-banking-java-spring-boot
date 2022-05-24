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

    public static class EmailFormatIsIncorrectException extends BaseException{
        public EmailFormatIsIncorrectException(String message) {
            super(message);
        }
    }

    public static class AccountNotValidException extends BaseException {
        public AccountNotValidException(String message) {
            super(message);
        }
    }
}
