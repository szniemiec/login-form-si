package handler;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class LoginHandler implements HttpHandler {

    public LoginHandler(PostgreSQLJDBC postgreSQLJDBC) {
        this.loginAccesDAO = new LoginAccesDAO(postgreSQLJDBC);
        this.formDataParser = new DataFormParser();
        this.cookieHelper = new CookieHelper();
        this.userDao = new UserDAO(postgreSQLJDBC);
        this.passHash = new PassHash();
    }

    public String getResponse() {
        return response;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            Map<String, String> inputs = DataFormParser.getData(httpExchange);
            String providedMail = inputs.get("login");
            String providedPassword = PassHash.encrypt(inputs.get("password"));

            try {
                User user = userDao.getLoggedUser(providedMail, providedPassword);
                ObjectMapper mapper = new ObjectMapper();
                response = mapper.writeValueAsString(user);
                sendResponse(response, httpExchange, 200);

            } catch (SQLException throwable) {
                throwable.printStackTrace();
                sendResponse("User not authorised", httpExchange, 401);
            }
        }
    }

    private void sendResponse(String response, HttpExchange exchange, int status) throws IOException {
        if (status == 200) {
            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
            exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
        }
        exchange.sendResponseHeaders(status, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}