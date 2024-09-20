package domain;

public record Instructor(String id, String name) {
    public Instructor {
        if (id == null || name == null) {
            throw new IllegalArgumentException("Instructor attributes cannot be null");
        }
    }
}
