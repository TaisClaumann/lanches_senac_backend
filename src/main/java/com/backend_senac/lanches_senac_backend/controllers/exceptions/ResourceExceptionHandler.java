package com.backend_senac.lanches_senac_backend.controllers.exceptions;

import com.backend_senac.lanches_senac_backend.services.exceptions.CpfInvalidoException;
import com.backend_senac.lanches_senac_backend.services.exceptions.RegistroJaCadastradoException;
import com.backend_senac.lanches_senac_backend.services.exceptions.RegistroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<StandardError> registroNaoEncontrado(RegistroNaoEncontradoException e){
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(RegistroJaCadastradoException.class)
    public ResponseEntity<StandardError> registroJaCadastrado(RegistroJaCadastradoException e){
        StandardError err = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<StandardError> registroJaCadastrado(CpfInvalidoException e){
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
