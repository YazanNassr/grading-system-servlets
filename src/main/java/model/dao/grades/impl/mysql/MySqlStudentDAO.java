package model.dao.grades.impl.mysql;

import domain.Lecture;
import domain.Student;
import model.dao.grades.StudentDAO;
import model.db.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MySqlStudentDAO implements StudentDAO {
    private final Database database;

    public MySqlStudentDAO(Database database) {
        this.database = database;
    }

    @Override
    public Student getStudent(String studentId) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "SELECT name FROM student WHERE id = ?");
            ps.setString(1, studentId);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return (new Student(studentId, resultSet.getString("name")));
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getStudentsAttending(Lecture lecture) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "SELECT studentId FROM lectureEnrollment WHERE lectureId = ?");
            ps.setString(1, lecture.id());

            ResultSet resultSet = ps.executeQuery();

            List<Student> students = new LinkedList<>();
            while (resultSet.next()) {
                students.add(
                        getStudent(resultSet.getString("studentId"))
                );
            }

            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createStudent(Student student) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "INSERT INTO student (id, name) VALUES (?, ?)");
            ps.setString(1, student.id());
            ps.setString(2, student.name());

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteStudent(String id) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "DELETE FROM student Where id = ?");
            ps.setString(1, id);

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }
    }
}
