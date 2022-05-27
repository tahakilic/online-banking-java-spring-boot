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


    public static class AccountNotValidException extends BaseException {
        public AccountNotValidException(String message) {
            super(message);
        }
    }

    public static class AmountNotValidException extends BaseException {
        public AmountNotValidException(String message) {
            super(message);
        }
    }

    public static class EmailNotValidException extends BaseException {
        public EmailNotValidException(String message) {
            super(message);
        }
    }

    public static class IbanNotValidException extends BaseException {
        public IbanNotValidException(String message) {
            super(message);
        }
    }

    public static class TransferNotValidException extends BaseException {
        public TransferNotValidException(String message) {
            super(message);
        }
    }
}
