package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Client connected from port " + socket.getPort());

            oos.writeObject("ready");

            int n = (Integer) ois.readObject();

            oos.writeObject("ready for messages");

            for (int i = 0; i < n; i++) {
                Message message = (Message) ois.readObject();
                System.out.println("Received message: " + message.getContent());
            }

            oos.writeObject("finished");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Client disconnected from port " + socket.getPort());
        }
    }
}