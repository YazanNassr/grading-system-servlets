package model.service;

import domain.Lecture;
import domain.Student;
import model.dao.grades.StudentDAO;
import model.dao.grades.impl.mysql.MySqlStudentDAO;
import model.db.Database;
import model.db.mysql.GradesDatabase;

import java.util.List;

public class StudentsService {
    private static final Database database = new GradesDatabase();
    private static final StudentDAO studentDAO = new MySqlStudentDAO(database);

    public static String getNameById(String id) {
        return getStudentById(id).name();
    }

    public static Student getStudentById(String id) {
        if (id == null) {
            throw new RuntimeException("Invalid ID: IDs cannot be null");
        }

        return studentDAO.getStudent(id);
    }

    public static List<Student> getStudentsAttending(Lecture lecture) {
        return studentDAO.getStudentsAttending(lecture);
    }

    public static boolean deleteStudent(String id) {
        return studentDAO.deleteStudent(id);
    }

    public static boolean createStudent(Student student) {
        return studentDAO.createStudent(student);
    }
}
