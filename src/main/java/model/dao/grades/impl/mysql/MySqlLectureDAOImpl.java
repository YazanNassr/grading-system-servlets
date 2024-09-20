package model.dao.grades.impl.mysql;

import domain.Instructor;
import domain.Lecture;
import domain.Student;
import model.dao.grades.LectureDAO;
import model.db.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MySqlLectureDAOImpl implements LectureDAO {
    private final Database database;

    public MySqlLectureDAOImpl(Database database) {
        this.database = database;
    }

    @Override
    public Lecture getLecture(String lectureId) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "SELECT courseId, instructorId FROM lecture WHERE id = ?");
            ps.setString(1, lectureId);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                var instructorDAO= new MySqlInstructorDAOImpl(database);
                var courseDAO = new MySqlCourseDAOImpl(database);

                return (new Lecture(lectureId,
                        courseDAO.getCourse(resultSet.getString("courseId")),
                        instructorDAO.getInstructor(resultSet.getString("instructorId"))
                ));
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Lecture> getLectures(Student student) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "SELECT lectureId FROM lectureEnrollment WHERE studentID = ?");
            ps.setString(1, student.id());

            ResultSet resultSet = ps.executeQuery();

            List<Lecture> lectures = new LinkedList<>();
            while (resultSet.next()) {
                lectures.add(
                        getLecture(resultSet.getString("lectureId"))
                );
            }

            return lectures;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Lecture> getLectures(Instructor instructor) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "SELECT id FROM lecture WHERE instructorId = ?");
            ps.setString(1, instructor.id());

            ResultSet resultSet = ps.executeQuery();

            List<Lecture> lectures = new LinkedList<>();
            while (resultSet.next()) {
                lectures.add(
                        getLecture(resultSet.getString("id"))
                );
            }

            return lectures;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createLecture(Lecture lecture) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "INSERT INTO lecture (id, courseId, instructorId) VALUES (?, ?, ?)");
            ps.setString(1, lecture.id());
            ps.setString(2, lecture.course().id());
            ps.setString(3, lecture.instructor().id());

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteLecture(String id) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "DELETE FROM lecture WHERE id = ?");
            ps.setString(1, id);

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }
    }
}
