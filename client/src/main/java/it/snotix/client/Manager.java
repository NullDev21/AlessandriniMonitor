package it.snotix.client;

import it.snotix.client.actions.actionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Manager {
    private static final String HOST = "localhost";
    private static final int PORT = 4040;

    public static void run() {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("client connected to server.");
            String message;

            while (true) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while ((message = bufferedReader.readLine()) != null) {
                    actionManager.searchAction(message);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            try {
                Thread.sleep(10000);
                Manager.run();
            } catch (Exception exception) {}
        }
    }
}
