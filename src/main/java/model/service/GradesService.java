package model.service;

import domain.Grade;
import domain.Lecture;
import domain.Student;
import model.dao.grades.LectureEnrollmentDAO;
import model.dao.grades.impl.mysql.MySqlLectureEnrollmentDAO;
import model.db.Database;
import model.db.mysql.GradesDatabase;

import java.util.LinkedList;
import java.util.List;

public class GradesService {
    private static final Database database = new GradesDatabase();
    private static final LectureEnrollmentDAO enrollmentDAO =
            new MySqlLectureEnrollmentDAO(database);

    public static List<Grade> getGrades(Student student) {
        List<Grade> grades = new LinkedList<>();

        List<Lecture> lectures = LecturesService.getLecturesAttendedBy(student);
        for (Lecture lecture : lectures) {
            grades.add(enrollmentDAO.getGrade(student, lecture));
        }

        return grades;
    }

    public static List<Grade> getGrades(Lecture lecture) {
        List<Grade> grades = new LinkedList<>();

        List<Student> students = StudentsService.getStudentsAttending(lecture);
        for (Student student : students) {
            grades.add(enrollmentDAO.getGrade(student, lecture));
        }

        return grades;
    }

    public static boolean updateMidtermGrade(Student student, Lecture lecture, Float newGrade) {
        return enrollmentDAO.updateMidtermGrade(student, lecture, newGrade);
    }

    public static boolean updateFinalsGrade(Student student, Lecture lecture, Float newGrade) {
        return enrollmentDAO.updateFinalsGrade(student, lecture, newGrade);
    }
}
