package com.wallacen.bff.agendador.business.exception;


public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UnauthorizedException(String msg) {
        super(msg);
    }
}
