package model.dao.grades;

import domain.Grade;
import domain.Lecture;
import domain.Student;

public interface LectureEnrollmentDAO {
    Grade getGrade(Student student, Lecture lecture);
    boolean enrol(Student student, Lecture lecture);
    boolean expel(Student student, Lecture lecture);
    boolean updateMidtermGrade(Student student, Lecture lecture, Float newGrade);
    boolean updateFinalsGrade(Student student, Lecture lecture, Float newGrade);
}
