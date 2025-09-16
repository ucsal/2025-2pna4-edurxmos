package br.com.mariojp.figureeditor;

public enum ShapeType {
    CIRCLE("Círculo"),
    RECTANGLE("Retângulo");

    private final String label;

    ShapeType(String label) {
        this.label = label;
    }

    @Override public String toString() {
        return label;
    }
}
