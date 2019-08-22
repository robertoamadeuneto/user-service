package br.com.maxplorer.userservice.core.domain.exception.constraint;

public class ConstraintViolationException extends RuntimeException {

    private final Constraint constraint;

    public ConstraintViolationException(Constraint constraint) {
        this.constraint = constraint;
    }

    public Constraint constraint() {
        return constraint;
    }
}
