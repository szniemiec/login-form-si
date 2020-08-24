package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBC {

    private Connection c;

    public PostgreSQLJDBC() {
        c = null;
    }

    public void connectToDatabase(DatabaseCredentials databaseCredentials) {
        String HOST = databaseCredentials.getHost();
        String PORT = databaseCredentials.getPort();
        String DATABASE = databaseCredentials.getDatabase();
        String LOGIN = databaseCredentials.getLogin();
        String PASSWORD = databaseCredentials.getPassword();

        try {
            c = DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE, LOGIN, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void disconnectFromDatabase() throws SQLException {
        this.c.close();
    }
    public Connection getConnection() {
        return c;
    }
}