package com.tutorial.javagraphql.exceptions;

import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Component
public class GlobalException {

    @ExceptionHandler(ConstraintViolationException.class)
    public ThrowableGraphQLError getAllErrors(Exception e){
        return new ThrowableGraphQLError(e);
    }
}
