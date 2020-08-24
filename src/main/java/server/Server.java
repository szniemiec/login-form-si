package server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    public void run() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.start();
    }
}
