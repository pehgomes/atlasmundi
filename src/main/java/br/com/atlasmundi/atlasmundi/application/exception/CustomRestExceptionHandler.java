package br.com.atlasmundi.atlasmundi.application.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomRestExceptionHandler {

    private final MessageSource messageSource;

    public CustomRestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
                                                               HttpHeaders headers, HttpStatus status,
                                                               WebRequest request) {

        if (e.getCause() instanceof InvalidFormatException) {

            final InvalidFormatException ex = (InvalidFormatException) e.getCause();

            if (ex.getTargetType().isEnum()) {

                final List<String> values = Arrays.asList(ex.getOriginalMessage().substring(
                                ex.getOriginalMessage().indexOf('[') + 1,
                                ex.getOriginalMessage().indexOf(']'))
                        .split(","));

                final String property = ex.getPathReference().substring(
                        ex.getPathReference().indexOf('[') + 2,
                        ex.getPathReference().indexOf(']') - 1);

                final In constraint = new In(property, ex.getValue());

                final String messageParam = String.join(",", values);

                final ConstraintViolationException constraintViolationException = new ConstraintViolationException(
                        property,
                        ex.getValue(),
                        constraint.messageKey(),
                        constraint,
                        Arrays.asList(new ConstraintParam("values", values)),
                        new Object[]{messageParam});
                return new ResponseEntity<>(buildError(constraintViolationException), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
        return new ResponseEntity<>(e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage(),
                headers, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> beanValidationConstraintViolationException(MethodArgumentNotValidException e) {
        return ResponseEntity.unprocessableEntity().body(new ApiError(
                e.getBindingResult().getFieldErrors().stream().map(field -> new ApiError.Error(
                        field.getField(),
                        "" + field.getRejectedValue(),
                        field.getDefaultMessage(),
                        new ApiError.Error.Constraint(field.getCode(), new ArrayList<>()))).collect(Collectors.toList())));
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<ApiError> beanValidationConstraintViolationException(javax.validation.ConstraintViolationException e) {

        final Set<String> excludedAttributes = new HashSet<>(Arrays.asList("groups", "payload", "message"));

        return ResponseEntity.unprocessableEntity().body(
                new ApiError(e.getConstraintViolations()
                        .stream()
                        .map(ex -> {

                            String message;

                            try {

                                message = messageSource.getMessage(
                                        ex.getMessage(),
                                        new Object[]{},
                                        LocaleContextHolder.getLocale());

                            } catch (NoSuchMessageException e2) {
                                message = ex.getMessage();
                            }

                            return new ApiError.Error(ex.getPropertyPath().toString(),
                                    ex.getInvalidValue(),
                                    message,
                                    new ApiError.Error.Constraint(
                                            ex.getConstraintDescriptor()
                                                    .getAnnotation()
                                                    .annotationType()
                                                    .getSimpleName(),
                                            ex.getConstraintDescriptor().getAttributes().entrySet()
                                                    .stream()
                                                    .filter(a -> !excludedAttributes.contains(a.getKey()))
                                                    .map(p -> new ApiError.Error.Constraint.Param(p.getKey(), p.getValue()))
                                                    .collect(Collectors.toList())));
                        })
                        .collect(Collectors.toList())));

    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<InternalServerException> internalServerException(InternalServerException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ApiError> businessLogicException(BusinessLogicException e) {
        return new ResponseEntity<>(buildError(e), HttpStatus.UNPROCESSABLE_ENTITY);

    }

    private ApiError buildError(ConstraintViolationException exception) {

        String message = "";
        try {
            message = messageSource.getMessage(exception.message(),
                    exception.args(),
                    LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            message = exception.getMessage();
        }
        return new ApiError(Arrays.asList(
                new ApiError.Error(exception.property(),
                        exception.value(),
                        message,
                        new ApiError.Error.Constraint(exception.constraint().name(),
                                exception.params()
                                        .stream()
                                        .map(p -> new ApiError.Error.Constraint.Param(
                                                p.getName(),
                                                p.getValue()))
                                        .collect(Collectors.toList())))));

    }

}
