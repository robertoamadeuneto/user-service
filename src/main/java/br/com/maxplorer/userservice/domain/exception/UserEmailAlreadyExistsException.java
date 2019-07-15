package br.com.maxplorer.userservice.domain.exception;

import br.com.maxplorer.userservice.domain.exception.constraint.Constraint;
import br.com.maxplorer.userservice.domain.exception.constraint.ConstraintViolationException;

public class UserEmailAlreadyExistsException extends ConstraintViolationException {

    public UserEmailAlreadyExistsException(Constraint constraint) {
        super(constraint);
    }
}
