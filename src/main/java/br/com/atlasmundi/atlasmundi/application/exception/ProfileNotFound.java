package br.com.atlasmundi.atlasmundi.application.exception;

public class ProfileNotFound implements Constraint {

    Object value;

    public ProfileNotFound(String value) {
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
