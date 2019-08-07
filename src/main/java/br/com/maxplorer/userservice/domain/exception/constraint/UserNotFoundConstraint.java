package br.com.maxplorer.userservice.domain.exception.constraint;

public class UserNotFoundConstraint extends Constraint {

    public UserNotFoundConstraint(String property, String value) {
        super(property, value);
    }
}
