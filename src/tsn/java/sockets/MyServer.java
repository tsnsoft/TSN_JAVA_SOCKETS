package tsn.java.sockets;

import java.io.*;
import java.net.*;

/**
 *
 * @author Талипов С.Н.
 */
public class MyServer {

    /**
     * ПРОГРАММА СЕРВЕР НА СОКЕТАХ TCP/IP: сообщение от клиента "stop"
     * останавливает сервер
     *
     * @throws java.io.IOException
     */
    public static void start() throws IOException {
        System.out.println("ПРОГРАММА СЕРВЕР НА СОКЕТАХ TCP/IP");

        ServerSocket server = null; // СОКЕТ-СЕРВЕР
        Socket client = null; // СОКЕТ-КЛИЕНТ
        final int port = 9737; // НОМЕР ПОРТА СЕРВЕРА

        BufferedReader in = null; // Входнй поток с клиента
        PrintWriter out = null; // Выходной поток на клиент

        try {
            server = new ServerSocket(port); // Создаем сокет сервера
        } catch (IOException e) {
            System.err.println("Не могу подключиться к порту " + port + "!");
            System.err.println("Завершение работы");
            System.exit(0);
        }

        try {

            while (true) { // Пока нет команды остановки - делаем бесконечный цикл

                try {
                    System.out.println("Ожидание подключения клиента ...");
                    client = server.accept();
                    System.out.println("Подключен клиент с адресом " + client.getInetAddress());
                } catch (IOException e) {
                    System.err.println("Ошибка при ожидании клиента");
                    System.err.println("Завершение работы");
                    System.exit(0);
                }

                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(), true);
                String messageFromClient;

                System.out.println("Ожидание задания от клиента ...");

                String st = null;
                int a = 0, b = 0; // Данные с клиента

                while ((messageFromClient = in.readLine()) != null) {
                    a = Integer.parseInt(messageFromClient);
                    break;
                }

                while ((messageFromClient = in.readLine()) != null) {
                    b = Integer.parseInt(messageFromClient);
                    break;
                }

                while ((messageFromClient = in.readLine()) != null) {
                    st = messageFromClient;
                    break;
                }

                System.out.println("Задание от клиента получено. Обработка задания ...");

                int x1 = a + b;
                int x2 = a * b;

                System.out.println("Передача ответа клиенту");

                out.println(x1);
                out.println(x2);

                out.close(); // Закрытие выходного потока на клиента
                in.close(); // Закрытие входного потока с клиента
                client.close(); // Закрытие клиента

                if (st.equalsIgnoreCase("stop")) { // Получена команда остановки сервера
                    break; // Остановка сервера
                }
            }

        } finally {
            server.close(); // Закрытие сервера
        }

        System.out.println("РАБОТА СЕРВЕРА ЗАВЕРШЕНА");
    }
}
