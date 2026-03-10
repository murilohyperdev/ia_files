package com.minhaempresa.meusaas.core.exception;

public class UnauthorizedException extends BusinessException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException() {
        super("Não autorizado");
    }
}
