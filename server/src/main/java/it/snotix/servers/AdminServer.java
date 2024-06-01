package it.snotix.servers;

import it.snotix.manager.AdminManager;
import it.snotix.panel.PanelService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class AdminServer {
    public static CompletableFuture<status> host() {
        return CompletableFuture.supplyAsync(() -> {
            final int PANEL_PORT = 9547;

            try {
                ServerSocket serverSocket = new ServerSocket(PANEL_PORT);
                System.out.println("panel port : " + PANEL_PORT);

                while (true) {
                    Socket con = serverSocket.accept();
                    AdminManager.addAdmin(con);

                    CompletableFuture.runAsync(() -> {PanelService.inizialize(con);});
                }

            } catch (IOException e) {
                e.printStackTrace();
                return status.ERROR;
            }

        });
    }

    public enum status {
        WORKING,
        ERROR
    }
}
