package tsn.java.sockets;

import java.io.IOException;

public class startServer {

    public static void main(String[] args) throws IOException {
        MyServer server = new MyServer();
        server.start();
    }
}
