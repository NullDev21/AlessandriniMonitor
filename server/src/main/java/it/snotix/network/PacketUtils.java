package it.snotix.network;

import it.snotix.manager.AdminManager;
import it.snotix.manager.BotManager;

import java.io.PrintWriter;
import java.net.Socket;

public class PacketUtils {

    public static void writeToBots (String str) {
        for (Socket connection : BotManager.getBots()) {
            try {
                PrintWriter printWriter = new PrintWriter(connection.getOutputStream(), true);
                printWriter.println(str);
            } catch (Exception e) {
                System.out.println("Error during writeString operation");
                e.printStackTrace();
            }
        }

        System.out.println("Write string to all bots.");
    }

    public static void writeToAdmins (String str) {
        for (Socket connection : AdminManager.getAdmins()) {
            try {
                PrintWriter printWriter = new PrintWriter(connection.getOutputStream(), true);
                printWriter.println(str);
            } catch (Exception e) {
                System.out.println("Error during writeString operation");
                e.printStackTrace();
            }
        }

        System.out.println("Write string to all admin");
    }

    public static void writeToSocket (Socket connection, String str) {
        try {
            PrintWriter printWriter = new PrintWriter(connection.getOutputStream(), true);
            printWriter.println(str);
        } catch (Exception e) {
            System.out.println("Error during writeString operation");
            e.printStackTrace();
        }
        System.out.println("[PacketUtils] packet sent.");
    }
}
