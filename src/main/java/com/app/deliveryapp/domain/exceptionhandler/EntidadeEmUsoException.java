package com.app.deliveryapp.domain.exceptionhandler;

public class EntidadeEmUsoException extends RuntimeException{

    public EntidadeEmUsoException(String message) {
        super(message);
    }
}
