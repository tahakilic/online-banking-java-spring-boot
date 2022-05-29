package com.patikadev.onlinebanking.exception;

public final class FacadeOperationException {
    public FacadeOperationException() {
    }

    public static class TransferNotValidException extends BaseException {
        public TransferNotValidException(String message) {
            super(message);
        }
    }
}
