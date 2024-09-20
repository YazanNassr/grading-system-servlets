package model.dao.authentication;

import domain.UserLogin;

import java.util.List;

public interface LoginDAO {
    boolean authenticate(UserLogin userLogin);
    List<String> getStudentsIds();
    List<String> getInstructorsIds();
    boolean createUser(UserLogin userLogin);
    boolean deleteUser(String id);
}
