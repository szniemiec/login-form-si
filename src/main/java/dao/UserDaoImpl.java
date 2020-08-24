package dao;

import database.PostgreSQLJDBC;
import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {

    @Override
    public User getLoggedUser(String email, String password) {
        final String SELECT_SQL = "SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "';";
        try {
            PostgreSQLJDBC database = new PostgreSQLJDBC();
            Statement st = database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(SELECT_SQL);
            if (rs.next()) {
                return createUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User();
    }

    private User createUser(ResultSet rs) throws SQLException {
        return new User().setId(rs.getInt("id"))
                .setEmail(rs.getString("email"))
                .setPassword(rs.getString("password"));
    }
}
