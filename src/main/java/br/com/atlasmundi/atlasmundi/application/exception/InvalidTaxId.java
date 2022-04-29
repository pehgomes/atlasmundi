package br.com.atlasmundi.atlasmundi.application.exception;

public class InvalidTaxId implements Constraint {

    Object value;

    public InvalidTaxId(String value) {
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
