package br.com.atlasmundi.atlasmundi.application.exception;

public class TaxIdAlreadyExists implements Constraint {

    Object value;

    public TaxIdAlreadyExists(String value) {
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
