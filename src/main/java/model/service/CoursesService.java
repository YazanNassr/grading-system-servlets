package model.service;

import domain.Course;
import model.dao.grades.CourseDAO;
import model.dao.grades.impl.mysql.MySqlCourseDAOImpl;
import model.db.Database;
import model.db.mysql.GradesDatabase;

public class CoursesService {
    private static final Database database = new GradesDatabase();
    private static final CourseDAO courseDao = new MySqlCourseDAOImpl(database);

    public static Course getCourseById(String id) {
        return courseDao.getCourse(id);
    }

    public static boolean createCourse(Course course) {
        return courseDao.createCourse(course);
    }

    public static boolean deleteCourse(String id) {
        return courseDao.deleteCourse(id);
    }
}
