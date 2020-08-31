package dao;

import models.User;

public interface UserDao {

    User getLoggedUser(String email, String password);

}
