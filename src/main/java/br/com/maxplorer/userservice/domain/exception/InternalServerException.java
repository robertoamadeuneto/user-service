package br.com.maxplorer.userservice.domain.exception;

public class InternalServerException extends RuntimeException {

    public InternalServerException(String message) {
        super(message);
    }
}
