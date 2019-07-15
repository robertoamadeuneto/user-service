package br.com.maxplorer.userservice.domain.exception.constraint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
public class ConstraintParam {

    private String name;
    private String value;
}
