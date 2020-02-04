package tsn.java.sockets;

import java.io.*;
import java.net.*;

public class MyClient {

    private Socket client = null; // СОКЕТ-КЛИЕНТ
    private String serverName;
    private final BufferedReader in = null;
    private final PrintWriter out = null;

    public void start(String[] args) throws IOException {

        final int port = 9737; // НОМЕР ПОРТА СЕРВЕРА
        final String LOCAL_HOST = "127.0.0.1"; // АДРЕС ТЕКЦЩЕГО КОМПЬЮТЕРА В СЕТИ

        System.out.println("ПРОГРАММА КЛИЕНТ НА СОКЕТАХ TCP/IP");
        if (args.length == 0) {
            System.out.println("Не указан адрес сервера! Используется текущий хост " + LOCAL_HOST);
            serverName = LOCAL_HOST;
        } else {
            serverName = args[0];
        }

        System.out.println("Подключение к серверу " + serverName + " на порту " + port + " ...");

        try {
            client = new Socket(serverName, port);
            System.out.println("Подключение к серверу успешно");
        } catch (IOException e) {
            System.err.println("Не могу подключиться к серверу!");
            System.err.println("Завершение работы");
            System.exit(0);
        }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            System.out.println("Передаем задание серверу:");

            int a = 10;
            int b = 20;

            System.out.println("a = " + a);
            System.out.println("b = " + b);

            out.println(a);
            out.println(b);
            out.println("stop");

            System.out.println("Ожидание ответа от сервера ...");

            String x1 = in.readLine();
            String x2 = in.readLine();

            System.out.println("СЕРВЕР ПОСЛАЛ ОТВЕТ: ");
            System.out.println("x1 = a + b = " + x1);
            System.out.println("x2 = a * b = " + x2);

        } catch (Exception e) {
            System.err.println("Не могу получить ответ от сервера!");
            System.err.println("Завершение работы");
            System.exit(0);
        }
    }

    public void stop() throws IOException {
        out.close(); // Закрываем выходной поток на сервер
        in.close();  // Закрываем входной поток с сервера
        client.close(); // Закрываем клиента
        System.out.println("РАБОТА КЛИЕНТА ЗАВЕРШЕНА");
    }

}
