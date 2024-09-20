package model.dao.grades;

import domain.Lecture;
import domain.Student;

import java.util.List;

public interface StudentDAO {
    Student getStudent(String studentId);
    List<Student> getStudentsAttending(Lecture lecture);
    boolean createStudent(Student student);
    boolean deleteStudent(String id);
}
