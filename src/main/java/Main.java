import dao.UserDaoImpl;
import server.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.run();

        UserDaoImpl userDao = new UserDaoImpl();
        userDao.getLoggedUser("test@test.pl", "Test12321");
    }
}
