package com.mgupta.oracle.entity;

public class DataEntityConversionException extends Exception {
    public DataEntityConversionException() {
        super();
    }

    public DataEntityConversionException(String message) {
        super(message);
    }

    public DataEntityConversionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
