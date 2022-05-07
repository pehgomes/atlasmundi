package br.com.atlasmundi.atlasmundi.application.exception;

public class EmailAlreadyInUse implements Constraint {

    Object value;

    public EmailAlreadyInUse(String value) {
        this.value = value;
    }

    @Override
    public String getProperty() {
        return null;
    }

    @Override
    public Object getValue() {
        return null;
    }
}
