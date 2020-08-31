package database;


import services.JSONService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBC {

    private Connection c;

    public PostgreSQLJDBC() {
        JSONService jsonService = new JSONService();
        DatabaseCredentials credentials = jsonService.readEnvironment();
        this.c = connect(credentials);
    }

    private Connection connect(DatabaseCredentials databaseCredentials) {
        String HOST = databaseCredentials.getHost();
        String PORT = databaseCredentials.getPort();
        String DATABASE = databaseCredentials.getDatabase();
        String LOGIN = databaseCredentials.getLogin();
        String PASSWORD = databaseCredentials.getPassword();
        try {
            this.c = DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE, LOGIN, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return this.c;
    }

    public void disconnect() throws SQLException {
        this.c.close();
    }

    public Connection getConnection() {
        return this.c;
    }
}