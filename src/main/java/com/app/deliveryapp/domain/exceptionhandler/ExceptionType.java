package com.app.deliveryapp.domain.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionType {

    ENITDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada"),

    ENTIDADE_EM_USO("Entidades associadas a outras entidades não podem ser deletadas", "/item-associado-a-outro"),

    MENSAGEM_BODY_IMCOMPREEMSIVEL("Mensagem incompreensivel", "/mensgem-imcompreensivel");

    private String title;
    private String uri;

    ExceptionType(String title, String path) {
        this.title = title;
        this.uri = "https://gio.com.br" + path;
    }
}
