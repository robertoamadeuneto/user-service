package br.com.maxplorer.userservice.core.domain.exception;

import br.com.maxplorer.userservice.core.domain.exception.constraint.Constraint;
import br.com.maxplorer.userservice.core.domain.exception.constraint.ConstraintViolationException;

public class UserEmailAlreadyExistsException extends ConstraintViolationException {

    public UserEmailAlreadyExistsException(final Constraint constraint) {
        super(constraint);
    }
}
