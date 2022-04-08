package br.com.atlasmundi.atlasmundi.application.exception;

public interface Constraint {

    default String messageKey() {
        return this.getClass().getName() + ".message";
    }

    default String name() {
        return this.getClass().getSimpleName();
    }

    String getProperty();

    Object getValue();

}