package model.service;

import domain.UserLogin;
import model.dao.authentication.LoginDAO;
import model.dao.authentication.impl.mysql.MySqlLoginDAOImpl;
import model.db.Database;
import model.db.mysql.AuthenticationDatabase;

import java.util.List;

public class UsersService {
    private static final Database database = new AuthenticationDatabase();
    private static final LoginDAO loginDao = new MySqlLoginDAOImpl(database);

    public static List<String>  getStudentsIds() {
        return loginDao.getStudentsIds();
    }

    public static List<String>  getInstructorsIds() {
        return loginDao.getInstructorsIds();
    }

    public static boolean createUser(UserLogin login) {
        if (login == null) {
            return false;
        }

        return loginDao.createUser(login);
    }

    public static boolean deleteUser(String id) {
        return loginDao.deleteUser(id);
    }
}
