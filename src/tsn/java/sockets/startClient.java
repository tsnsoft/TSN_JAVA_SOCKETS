package tsn.java.sockets;

import java.io.IOException;

public class startClient {

    public static void main(String[] args) throws IOException {
        MyClient client = new MyClient();
        client.start(args);
        //client.stop();
    }
}
