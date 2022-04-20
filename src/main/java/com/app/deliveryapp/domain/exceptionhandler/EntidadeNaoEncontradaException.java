package com.app.deliveryapp.domain.exceptionhandler;

public class EntidadeNaoEncontradaException extends  RuntimeException{
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
