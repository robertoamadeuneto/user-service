package br.com.maxplorer.userservice.core.domain.exception.constraint;

public class UserNotFoundConstraint extends Constraint {

    public UserNotFoundConstraint(final String property, final String value) {
        super(property, value);
    }
}
