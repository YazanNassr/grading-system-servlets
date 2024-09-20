package model.dao.grades.impl.mysql;

import domain.Instructor;
import model.dao.grades.InstructorDAO;
import model.db.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlInstructorDAOImpl implements InstructorDAO {
    private final Database database;

    public MySqlInstructorDAOImpl(Database database) {
        this.database = database;
    }

    @Override
    public Instructor getInstructor(String instructorId) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "SELECT name FROM instructor WHERE id = ?");
            ps.setString(1, instructorId);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return (new Instructor(instructorId, resultSet.getString("name")));
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createInstructor(Instructor instructor) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "INSERT INTO instructor (id, name) VALUES (?, ?)");
            ps.setString(1, instructor.id());
            ps.setString(2, instructor.name());

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteInstructor(String id) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "DELETE FROM instructor WHERE id = ?");
            ps.setString(1, id);

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }
    }
}
