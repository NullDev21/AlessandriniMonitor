import it.snotix.network.KeepAliveSystem;
import it.snotix.servers.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Bootstrap {

    public static void main(String[] args) throws InterruptedException {
        runAdminServer();
        runClientServer();


        while (true) {
            Thread.sleep(100000000);
        }
    }

    private static void runAdminServer() {
        // Server admin
        CompletableFuture<AdminServer.status> adminServer;

        adminServer = AdminServer.host().exceptionally(throwable -> {
            System.err.println("error while admin server running: " + throwable.toString());
            return AdminServer.status.ERROR;
        });

        adminServer.thenAcceptAsync(status -> {
            KeepAliveSystem.pingAdmins();
        });

        adminServer.complete(AdminServer.status.WORKING);

    }

    public static void runClientServer() {
        //Server bot
        CompletableFuture<BotServer.status> botServer = BotServer.host().exceptionally(throwable -> {
            System.err.println("error while bot server running: " + throwable.getMessage());
            return BotServer.status.ERROR;
        });

        botServer.complete(BotServer.status.WORKING);

        botServer.thenAcceptAsync(status -> {
            if (status == BotServer.status.WORKING) {
                CompletableFuture.runAsync(KeepAliveSystem::pingBots);
            }
        });
    }
}