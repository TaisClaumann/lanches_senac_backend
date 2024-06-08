package com.backend_senac.lanches_senac_backend.services.exceptions;

public class CpfInvalidoException extends RuntimeException {

    public CpfInvalidoException(String msg) {
        super(msg);
    }

    public CpfInvalidoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
