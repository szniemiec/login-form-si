package server;

import com.sun.net.httpserver.HttpServer;
import handler.LoginHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    public void run() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/login", new LoginHandler());
        server.start();
        System.out.println("Server has started on port " + server.getAddress().getPort());
    }
}
