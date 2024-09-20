package model.service;

import domain.Instructor;
import model.dao.grades.InstructorDAO;
import model.dao.grades.impl.mysql.MySqlInstructorDAOImpl;
import model.db.Database;
import model.db.mysql.GradesDatabase;

public class InstructorsService {
    private static final Database database = new GradesDatabase();
    private static final InstructorDAO instructorDao = new MySqlInstructorDAOImpl(database);

    public static Instructor getInstructorById(String id) {
        return instructorDao.getInstructor(id);
    }

    public static boolean createInstructor(Instructor instructor) {
        return instructorDao.createInstructor(instructor);
    }

    public static boolean deleteInstructor(String id) {
        return instructorDao.deleteInstructor(id);
    }

}
