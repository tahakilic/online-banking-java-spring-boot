package com.patikadev.onlinebanking.exception;

public final class ServiceOperationException {
    public ServiceOperationException() {
    }

    public static class CustomerNotValidException extends BaseException{
        public CustomerNotValidException(String message) {
            super(message);
        }
    }

    public static class CustomerAddressNotValidException extends BaseException {
        public CustomerAddressNotValidException(String message) {
            super(message);
        }
    }

    public static class AccountNotValidException extends BaseException {
        public AccountNotValidException(String message) {
            super(message);
        }
    }

    public static class AccountBalanceNotEmpty extends BaseException {
        public AccountBalanceNotEmpty(String message) {
            super(message);
        }
    }

    public static class TransferNotValidException extends BaseException {
        public TransferNotValidException(String message) {
            super(message);
        }
    }

    public static class CardNotValidException extends BaseException {
        public CardNotValidException(String message) {
            super(message);
        }
    }
}
