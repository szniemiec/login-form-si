package dao;

import models.User;

public interface UserDao {

    public User getLoggedUser(String email, String password);

}
