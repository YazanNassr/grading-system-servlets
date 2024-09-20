package domain;

public record Lecture(String id, Course course, Instructor instructor) {
    public Lecture {
        if (id == null || course == null || instructor == null) {
            throw new IllegalArgumentException("Lecture attributes cannot be null.");
        }
    }
}