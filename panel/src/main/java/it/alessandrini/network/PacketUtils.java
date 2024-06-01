package it.alessandrini.network;

import java.io.*;
import java.net.Socket;

public class PacketUtils {
    static Socket connection;
    static final String HOST = "localhost";
    static final int PORT = 9547;

    public static void openConnection() {
        try {
            connection = new Socket("127.0.0.1", 9547);
        } catch (Exception e) {
            System.out.println("error during socket connection");
            e.printStackTrace();
        }
    }

    public static void writeToServer(String message) {
        try {
            PrintWriter printWriter = new PrintWriter(connection.getOutputStream(), true);
            printWriter.println(message);
        } catch (Exception e) {
            System.out.println("error during write to server information ");
        }
    }

    public static String receivedFromServer() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error during packet riciving : ");
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
}
