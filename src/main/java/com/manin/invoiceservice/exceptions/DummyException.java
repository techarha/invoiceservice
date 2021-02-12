package com.manin.invoiceservice.exceptions;

public class DummyException extends RuntimeException{
    private String errorCode;

    public DummyException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
