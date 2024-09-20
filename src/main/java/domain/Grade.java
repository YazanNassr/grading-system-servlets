package domain;

public record Grade(Student student, Lecture lecture, Float midtermExam, Float finalExam) {
    public Grade {
        if (student == null) {
            throw new IllegalArgumentException("Grade: 'student' attribute cannot be null.");
        }

        if (lecture == null) {
            throw new IllegalArgumentException("Grade: 'lecture' attribute cannot be null.");
        }

        if (notValidGrade(midtermExam) || notValidGrade(finalExam)) {
            throw new IllegalArgumentException("Grade: grades cannot be negative.");
        }
    }

    private static boolean notValidGrade(Float grade) {
        return (grade != null && grade.compareTo(Float.valueOf(0)) < 0);
    }
}
