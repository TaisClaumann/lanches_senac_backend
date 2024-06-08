package com.backend_senac.lanches_senac_backend.services.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(String msg) {
        super(msg);
    }

    public RegistroNaoEncontradoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
