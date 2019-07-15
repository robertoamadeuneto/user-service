package br.com.maxplorer.userservice.domain.exception.constraint;

public class UserEmailAlreadyExistsConstraint extends Constraint {

    public UserEmailAlreadyExistsConstraint(String property, String value) {
        super(property, value);
    }
}
