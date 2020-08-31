package handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.UserDaoImpl;
import helpers.DataFormParser;
import models.User;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;

public class LoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        String response = "";
        int rCode = 0;

        if (requestMethod.equals("GET")) {
            response = "ok";
            rCode = 200;
        } else if (requestMethod.equals("POST")) {
            Map<String, String> inputs = DataFormParser.getData(exchange);
            String providedMail = inputs.get("email");
            String providedPassword = inputs.get("password");
            ObjectMapper mapper = new ObjectMapper();

            try {
                UserDaoImpl userDao = new UserDaoImpl();
                User user = userDao.getLoggedUser(providedMail, providedPassword);
                if (user.getEmail().equals(providedMail) && user.getPassword().equals(providedPassword)) {
                    rCode = 200;
                } else {
                    rCode = 401;
                }
                response = mapper.writeValueAsString(user);
            } catch (Exception e) {
                response = e.getMessage();
            }
        }
        if (rCode == 200) {
            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
            exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
        }
        exchange.sendResponseHeaders(rCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}