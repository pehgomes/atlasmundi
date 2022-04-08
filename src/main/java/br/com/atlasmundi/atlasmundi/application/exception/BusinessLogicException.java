package br.com.atlasmundi.atlasmundi.application.exception;

public class BusinessLogicException extends ConstraintViolationException {

    private static final long serialVersionUID = -4859913410364892605L;

    public BusinessLogicException(Constraint constraint) {
        super(constraint,
                constraint.getProperty(),
                constraint.getValue());
    }

}
