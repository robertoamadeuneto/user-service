package br.com.maxplorer.userservice.core.domain.exception;

public class UserNotActiveException extends RuntimeException {

    public UserNotActiveException(final String message) {
        super(message);
    }
}
