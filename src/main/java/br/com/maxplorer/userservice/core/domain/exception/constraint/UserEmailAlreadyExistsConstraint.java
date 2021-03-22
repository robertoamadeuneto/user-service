package br.com.maxplorer.userservice.core.domain.exception.constraint;

public class UserEmailAlreadyExistsConstraint extends Constraint {

    public UserEmailAlreadyExistsConstraint(final String property, final String value) {
        super(property, value);
    }
}
