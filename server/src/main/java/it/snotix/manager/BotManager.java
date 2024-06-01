package it.snotix.manager;

import java.net.Socket;
import java.util.ArrayList;

public class BotManager {
    private static final ArrayList<Socket> botSockets = new ArrayList<>();

    public static void addBot (Socket bot) {
        botSockets.add(bot);
        System.out.println("[+] bot added! ( " + bot.getInetAddress() + " )");
    }

    public static void removeBot (Socket bot) {
        try {
            botSockets.remove(bot);
            System.out.println("[-] bot removed! (" + bot.getInetAddress() +" )");
            bot.close();
        } catch (Exception e) {
            System.out.println("error during bot removing : " + e);
        }
    }

    public static ArrayList<Socket> getBots () {
        return botSockets;
    }
}
