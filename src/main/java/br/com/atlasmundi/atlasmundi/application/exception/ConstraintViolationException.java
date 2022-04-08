package br.com.atlasmundi.atlasmundi.application.exception;

import java.util.ArrayList;
import java.util.List;

public class ConstraintViolationException extends RuntimeException {

    private static final long serialVersionUID = 9057974208746966920L;

    private final String property;
    private final Object value;
    private final Constraint constraint;
    private final List<ConstraintParam> params;
    private final Object[] messageParams;

    ConstraintViolationException(String property,
                                 Object value,
                                 String message,
                                 Constraint constraint,
                                 List<ConstraintParam> params,
                                 Object[] messageParams) {

        super(message);
        this.property = property;
        this.value = value;
        this.constraint = constraint;
        this.params = params == null ? new ArrayList<>() : params;
        this.messageParams = messageParams;

    }

    public ConstraintViolationException(String message, Constraint constraint) {
        this(null, null, message, constraint, null, null);
    }

    public ConstraintViolationException(Constraint constraint, String propertyName, Object value) {
        this(propertyName, value, constraint.messageKey(), constraint, null, null);
    }

    public String property() {
        return property;
    }

    public Object value() {
        return value;
    }

    public String message() {
        return constraint.messageKey();
    }

    public Constraint constraint() {
        return constraint;
    }

    List<ConstraintParam> params() {
        return params;
    }

    Object[] args() {
        return messageParams;
    }

}
