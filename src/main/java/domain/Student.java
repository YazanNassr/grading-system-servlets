package domain;

public record Student(String id, String name) {
    public Student {
        if (id == null || name == null) {
            throw new IllegalArgumentException("Student attributes cannot be null");
        }
    }
}
