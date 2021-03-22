package br.com.maxplorer.userservice.core.domain.exception.constraint;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConstraintViolationException extends RuntimeException {

    private final Constraint constraint;

    public Constraint constraint() {
        return constraint;
    }
}
