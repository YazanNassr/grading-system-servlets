package model.dao.grades;

import domain.Instructor;

public interface InstructorDAO {
    Instructor  getInstructor(String instructorId);
    boolean createInstructor(Instructor instructor);
    boolean deleteInstructor(String id);
}
