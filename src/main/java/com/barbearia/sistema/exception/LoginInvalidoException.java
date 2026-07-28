package com.barbearia.sistema.exception;

public class LoginInvalidoException extends RuntimeException {
    public LoginInvalidoException(String messagem) {
        super(messagem);
    }
}
