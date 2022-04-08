package br.com.atlasmundi.atlasmundi.application.exception;

public class In implements Constraint {

    private final String property;
    private final Object value;

    public In(String property, Object value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public String getProperty() {
        return property;
    }

    @Override
    public Object getValue() {
        return value;
    }

}
