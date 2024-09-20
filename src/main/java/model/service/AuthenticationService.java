package model.service;

import domain.UserLogin;
import model.dao.authentication.impl.mysql.MySqlLoginDAOImpl;
import model.db.Database;
import model.db.mysql.AuthenticationDatabase;

public class AuthenticationService {
    private static final Database authDatabase = new AuthenticationDatabase();
    private static final MySqlLoginDAOImpl loginDAO = new MySqlLoginDAOImpl(authDatabase);

    public static boolean authenticate(UserLogin login) {
        if (login == null) {
            return false;
        }

        return loginDAO.authenticate(login);
    }
}
