package br.com.atlasmundi.atlasmundi.application.exception;

public class InviteNotFoundForCustomer implements Constraint {

    Object value;

    public InviteNotFoundForCustomer(String value) {
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
