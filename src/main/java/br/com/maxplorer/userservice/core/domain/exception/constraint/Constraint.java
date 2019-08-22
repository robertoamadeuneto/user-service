package br.com.maxplorer.userservice.core.domain.exception.constraint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public abstract class Constraint {

    private String property;
    private String value;

    public String messageKey() {
        return this.getClass().getName();
    }

    public String name() {
        return this.getClass().getSimpleName();
    }
}
