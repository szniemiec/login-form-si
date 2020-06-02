import database.DatabaseCredentials;
import database.PostgreSQLJDBC;
import services.JSONService;

public class Main {

    public static void main(String[] args) {
        JSONService jsonService = new JSONService();
        DatabaseCredentials databaseCredentials = jsonService.readEnviroment();
        PostgreSQLJDBC database = new PostgreSQLJDBC();
        database.connectToDatabase(databaseCredentials);
    }
}
