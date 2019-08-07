package br.com.maxplorer.userservice.domain.exception;

import br.com.maxplorer.userservice.domain.exception.constraint.Constraint;
import br.com.maxplorer.userservice.domain.exception.constraint.ConstraintViolationException;

public class UserNotFoundException extends ConstraintViolationException {

    public UserNotFoundException(Constraint constraint) {
        super(constraint);
    }
}
