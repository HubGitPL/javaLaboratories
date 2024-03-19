package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            String serverMessage = (String) ois.readObject();
            System.out.println("Server: " + serverMessage);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number of messages to send:");
            int n = scanner.nextInt();
            oos.writeObject(n);

            serverMessage = (String) ois.readObject();
            System.out.println("Server: " + serverMessage);

            for (int i = 0; i < n; i++) {
                System.out.println("Enter message number " + (i+1) + ":");
                int messageContent = scanner.nextInt();
                Message message = new Message(Integer.toString(messageContent));
                oos.writeObject(message);
            }

            serverMessage = (String) ois.readObject();
            System.out.println("Server: " + serverMessage);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 1234);
        client.start();
    }
}