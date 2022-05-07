package br.com.atlasmundi.atlasmundi.application.exception;

public class LoginAlreadyInUse implements Constraint {

    Object value;

    public LoginAlreadyInUse(String value) {
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
