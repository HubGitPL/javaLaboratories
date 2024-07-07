package org.example;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            //Dla kazdego nowego klienta tworzony jest nowy watek
            ////uruchamia nowy watek i wywoluje metoda run
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}