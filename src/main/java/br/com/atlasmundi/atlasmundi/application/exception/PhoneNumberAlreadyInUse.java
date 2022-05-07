package br.com.atlasmundi.atlasmundi.application.exception;

public class PhoneNumberAlreadyInUse implements Constraint {

    Object value;

    public PhoneNumberAlreadyInUse(String value) {
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
