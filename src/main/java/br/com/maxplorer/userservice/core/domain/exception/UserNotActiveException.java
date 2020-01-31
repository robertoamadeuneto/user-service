package br.com.maxplorer.userservice.core.domain.exception;

public class UserNotActiveException extends RuntimeException {

    public UserNotActiveException(String message) {
        super(message);
    }
}
