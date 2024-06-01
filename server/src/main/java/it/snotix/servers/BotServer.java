package it.snotix.servers;

import it.snotix.manager.BotManager;
import it.snotix.network.KeepAliveSystem;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class BotServer {

    public static CompletableFuture<status> host() {
        return CompletableFuture.supplyAsync(() -> {
            final int CLIENTS_PORT = 4040;

            try {
                ServerSocket serverSocket = new ServerSocket(CLIENTS_PORT);
                System.out.println("bot port : " + CLIENTS_PORT);

                while (true) {
                    Socket con = serverSocket.accept();
                    BotManager.addBot(con);
                }

            } catch (IOException e) {
                return status.ERROR;
            }
        });
    }

    public enum status {
        WORKING,
        ERROR;
    }
}
