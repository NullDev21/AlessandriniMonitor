package it.snotix.manager;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class SecurityManager {
    static ArrayList<Socket> autorizedAdmins = new ArrayList<>();

    public static void kickConnection (Socket socket , String reason) {
        try {
            System.out.println("[SecuritySystem] removing connection for : " + reason);
            socket.close();
        } catch (Exception e) {
            System.out.println("[SecuritySystem] error while SecurityManager kickConnection " + e);
        }
    }

    public static void addAutorizedAdmin (Socket connection) {
        autorizedAdmins.add(connection);
    }

    public static void removeAutorizedAdmin (Socket connection) {
        autorizedAdmins.remove(connection);
    }

    public static boolean isAutorizedAdmin (Socket connection) {
        boolean autorized = false;

        for (Socket socket : autorizedAdmins) {
            if (Objects.equals(socket, connection)) {
                autorized = true;
                return autorized;
            }
        }

        return false;
    }
}
