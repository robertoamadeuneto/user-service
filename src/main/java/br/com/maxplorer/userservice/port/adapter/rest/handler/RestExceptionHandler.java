package br.com.maxplorer.userservice.port.adapter.rest.handler;

import br.com.maxplorer.userservice.domain.exception.UserEmailAlreadyExistsException;
import br.com.maxplorer.userservice.domain.exception.constraint.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class RestExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    public ResponseEntity<RestExceptionDetails> handleUserEmailAlreadyExistsException(UserEmailAlreadyExistsException exception) {
        return new ResponseEntity<>(toRestExceptionDetails(exception), HttpStatus.CONFLICT);
    }

    private <T extends ConstraintViolationException> RestExceptionDetails toRestExceptionDetails(T exception) {
        return new RestExceptionDetails(Collections.singletonList(new RestExceptionDetails.Detail(exception.constraint().property(),
                exception.constraint().value(),
                messageSource.getMessage(exception.constraint().messageKey(), null, LocaleContextHolder.getLocale()),
                new RestExceptionDetails.Detail.Constraint(exception.constraint().name()))));
    }
}
