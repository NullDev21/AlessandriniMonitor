package it.snotix.security;

import it.snotix.manager.SecurityManager;
import it.snotix.network.PacketUtils;

import java.net.Socket;

public class LicenseSystem {
    public static void checkCredentials (String message, Socket adminConnection) {
        try {
            String[] splitter = message.split(" ");
            String key = splitter[1];

            // todo :: add simple system for key access
            String keys = "ABC-123 CDE-435"; // only for now

            if (keys.contains(key)) {
                PacketUtils.writeToSocket(adminConnection, "true");
                SecurityManager.addAutorizedAdmin(adminConnection);
                System.out.println("[LicenseSystem] successfully check passed. ( " + adminConnection.getInetAddress() + " )");
            } else {
                PacketUtils.writeToSocket(adminConnection, "false");
                Thread.sleep(3000);
                SecurityManager.kickConnection(adminConnection, "wrong license.");
            }
        } catch (Exception e) {
            System.out.println("error in license system");
        }

    }
}
