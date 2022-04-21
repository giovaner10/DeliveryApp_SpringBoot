package com.app.deliveryapp.domain.exceptionhandler;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class EntidadeNaoEncontradaException extends  RuntimeException {

    private Long idDaBusca;
    public EntidadeNaoEncontradaException(String message, Long id) {
        super(message);
        this.idDaBusca = id;
    }
}
