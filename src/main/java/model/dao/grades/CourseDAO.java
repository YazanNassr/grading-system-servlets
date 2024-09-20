package model.dao.grades;

import domain.Course;
import domain.Instructor;

public interface CourseDAO {
    Course getCourse(String id);
    Course getCourseCoordinatedBy(Instructor instructor);
    boolean createCourse(Course course);
    boolean deleteCourse(String id);
}
