package br.com.constock.userservice.domain.exception;

public class UserEmailAlreadyExistsException extends RuntimeException {

    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }
}
