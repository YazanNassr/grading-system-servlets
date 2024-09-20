package model.dao.grades.impl.mysql;

import domain.Course;
import domain.Instructor;
import model.dao.grades.CourseDAO;
import model.dao.grades.InstructorDAO;
import model.db.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlCourseDAOImpl implements CourseDAO {
    private final Database database;

    public MySqlCourseDAOImpl(Database database) {
        this.database = database;
    }

    @Override
    public Course getCourse(String id) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "SELECT name, coordinatorId FROM course WHERE id = ?");
            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String coordinatorId = resultSet.getString("coordinatorId");

                InstructorDAO instructorDao = new MySqlInstructorDAOImpl(database);

                return new Course(id, name, instructorDao.getInstructor(coordinatorId));
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course getCourseCoordinatedBy(Instructor instructor) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "SELECT id, name FROM course WHERE coordinatorId = ?");
            ps.setString(1, instructor.id());

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");

                return new Course(id, name, instructor);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createCourse(Course course) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "INSERT INTO course (id, name, coordinatorId) VALUES (?, ?, ?)");
            ps.setString(1, course.id());
            ps.setString(2, course.name());
            ps.setString(3, course.instructor().id());

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteCourse(String id) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "DELETE FROM course WHERE id = ?");
            ps.setString(1, id);

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }

    }
}
