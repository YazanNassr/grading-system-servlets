package model.service;

import domain.Lecture;
import domain.Student;
import model.dao.grades.LectureEnrollmentDAO;
import model.dao.grades.impl.mysql.MySqlLectureEnrollmentDAO;
import model.db.Database;
import model.db.mysql.GradesDatabase;

public class EnrollmentService {
    private static final Database database = new GradesDatabase();
    private static final LectureEnrollmentDAO enrollmentDao
            = new MySqlLectureEnrollmentDAO(database);

    public static boolean enrol(Student student, Lecture lecture) {
        return enrollmentDao.enrol(student, lecture);
    }

    public static boolean expel(Student student, Lecture lecture) {
        return enrollmentDao.expel(student, lecture);
    }
}
