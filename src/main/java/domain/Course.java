package domain;

public record Course(String id, String name, Instructor instructor) {
    public Course {
        if (id == null || name == null || instructor == null) {
            throw new IllegalArgumentException("Course attributes cannot be null.");
        }
    }
}
