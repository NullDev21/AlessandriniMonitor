package it.snotix.manager;

import java.net.Socket;
import java.util.ArrayList;

public class AdminManager {
    private static final ArrayList<Socket> adminSockets = new ArrayList<>();

    public static void addAdmin (Socket bot) {
        adminSockets.add(bot);
        System.out.println("[+] admin connection! ( " + bot.getInetAddress() + " )");
    }

    public static void removeAdmin (Socket admin) {
        try {
            adminSockets.remove(admin);
            System.out.println("[-] admin connection! ( " + admin.getInetAddress() + " )");

            if (SecurityManager.isAutorizedAdmin(admin)) {
                SecurityManager.removeAutorizedAdmin(admin);
            }

            admin.close();
        } catch (Exception e) {
            System.out.println("error during remove admin");
        }
    }

    public static ArrayList<Socket> getAdmins () {
        return adminSockets;
    }
}
