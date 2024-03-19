package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            System.out.println( (String) ois.readObject());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number of messages to send:");
            int n = scanner.nextInt();
            oos.writeObject(n);

            System.out.println((String) ois.readObject());

            for (int i = 0; i < n; i++) {
                System.out.println("Enter the content of message " + (i + 1) + ":");
                String content = scanner.next();
                oos.writeObject(new Message(i+1, content));
            }

            System.out.println((String) ois.readObject());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}