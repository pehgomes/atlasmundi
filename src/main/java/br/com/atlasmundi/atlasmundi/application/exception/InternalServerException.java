package br.com.atlasmundi.atlasmundi.application.exception;

public class InternalServerException extends RuntimeException {

    private static final long serialVersionUID = -2026068994629918064L;

    public InternalServerException(Throwable e) {
        super(e);
    }

    public InternalServerException(String message, Throwable e) {
        super(message, e);
    }

}