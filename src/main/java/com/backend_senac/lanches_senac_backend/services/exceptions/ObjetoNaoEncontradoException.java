package com.backend_senac.lanches_senac_backend.services.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException {

    public ObjetoNaoEncontradoException(String msg) {
        super(msg);
    }

    public ObjetoNaoEncontradoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
