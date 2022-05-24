package com.patikadev.onlinebanking.exception;

public final class ServiceOperationException {
    public ServiceOperationException() {
    }

    public static class CustomerNotValidException extends BaseException{
        public CustomerNotValidException(String message) {
            super(message);
        }
    }
}
