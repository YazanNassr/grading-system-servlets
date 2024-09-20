package model.service;

import domain.Instructor;
import domain.Lecture;
import domain.Student;
import model.dao.grades.LectureDAO;
import model.dao.grades.impl.mysql.MySqlLectureDAOImpl;
import model.db.Database;
import model.db.mysql.GradesDatabase;

import java.util.List;

public class LecturesService {
    private static final Database database = new GradesDatabase();
    private static final LectureDAO lectureDAO = new MySqlLectureDAOImpl(database);

    public static Lecture getLectureById(String id) {
        return lectureDAO.getLecture(id);
    }

    public static List<Lecture> getLecturesInstructedBy(Instructor instructor) {
        if (instructor == null) {
            throw new RuntimeException("Lectures Service: Student Cannot be null.");
        }

        return lectureDAO.getLectures(instructor);
    }

    public static List<Lecture> getLecturesAttendedBy(Student student) {
        if (student == null) {
            throw new RuntimeException("Lectures Service: Student Cannot be null.");
        }

        return lectureDAO.getLectures(student);
    }

    public static boolean createLecture(Lecture lecture) {
        return lectureDAO.createLecture(lecture);
    }

    public static boolean deleteLecture(String id) {
        return lectureDAO.deleteLecture(id);
    }
}
