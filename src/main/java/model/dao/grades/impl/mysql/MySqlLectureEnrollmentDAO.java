package model.dao.grades.impl.mysql;

import domain.Grade;
import domain.Lecture;
import domain.Student;
import model.dao.grades.LectureEnrollmentDAO;
import model.db.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlLectureEnrollmentDAO implements LectureEnrollmentDAO {
    private final Database database;

    public MySqlLectureEnrollmentDAO(Database database) {
        this.database = database;
    }

    @Override
    public Grade getGrade(Student student, Lecture lecture) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "SELECT midTermGrade, finalGrade FROM lectureEnrollment WHERE lectureId = ? AND studentId = ?");
            ps.setString(1, lecture.id());
            ps.setString(2, student.id());

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return (new Grade(student, lecture,
                        resultSet.getFloat("midTermGrade"), resultSet.getFloat("finalGrade")));
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean enrol(Student student, Lecture lecture) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "INSERT INTO lectureEnrollment (lectureId, studentId) VALUES (?, ?)");
            ps.setString(1, lecture.id());
            ps.setString(2, student.id());

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean expel(Student student, Lecture lecture) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "DELETE FROM lectureEnrollment WHERE lectureId = ? AND studentId = ?");
            ps.setString(1, lecture.id());
            ps.setString(2, student.id());

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean updateMidtermGrade(Student student, Lecture lecture, Float newGrade) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "UPDATE lectureEnrollment SET midtermGrade = ? WHERE lectureId = ? AND studentId = ?");
            ps.setFloat(1, newGrade);
            ps.setString(2, lecture.id());
            ps.setString(3, student.id());

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean updateFinalsGrade(Student student, Lecture lecture, Float newGrade) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "UPDATE lectureEnrollment SET finalGrade = ? WHERE lectureId = ? AND studentId = ?");
            ps.setFloat(1, newGrade);
            ps.setString(2, lecture.id());
            ps.setString(3, student.id());

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }
    }
}
