package model.dao.authentication.impl.mysql;

import domain.UserLogin;
import model.dao.authentication.LoginDAO;
import model.db.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

// TODO turn this into inheritance. Open Closed principle.
//  make getPreparedStatement abstract

public class MySqlLoginDAOImpl implements LoginDAO {
    private final Database database;

    public MySqlLoginDAOImpl(Database database) {
        this.database = database;
    }

    @Override
    public boolean authenticate(UserLogin userLogin) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "SELECT id FROM login WHERE id = ? AND password = ? AND type = ?");
            ps.setString(1, userLogin.id());
            ps.setString(2, userLogin.password());
            ps.setString(3, userLogin.type());

            ResultSet resultSet = ps.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getStudentsIds() {

        try (Connection connection = database.getConnection()) {
            List<String> res = new LinkedList<>();

            var ps = connection.prepareStatement("SELECT id FROM login WHERE type = 'student'");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                res.add(resultSet.getString("id"));
            }

            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getInstructorsIds() {
        try (Connection connection = database.getConnection()) {
            List<String> res = new LinkedList<>();

            var ps = connection.prepareStatement("SELECT id FROM login WHERE type = 'instructor'");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                res.add(resultSet.getString("id"));
            }

            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createUser(UserLogin userLogin) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "INSERT INTO login (id, password, type) VALUES (?, ?, ?)");
            ps.setString(1, userLogin.id());
            ps.setString(2, userLogin.password());
            ps.setString(3, userLogin.type());

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(String id) {
        try (Connection connection = database.getConnection()) {
            var ps = connection.prepareStatement(
                    "DELETE FROM login WHERE id = ?");
            ps.setString(1, id);

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            return false;
        }

    }
}
