package it.snotix.network;

import it.snotix.manager.AdminManager;
import it.snotix.manager.BotManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class KeepAliveSystem  {
    public static void pingBots () {
        while (true) {
            for (Socket bot : BotManager.getBots()) {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(bot.getInputStream()));
                    in.readLine();
                } catch (Exception e) {
                    BotManager.removeBot(bot);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    public static void pingAdmins() {
        while (true) {
            for (Socket admin : AdminManager.getAdmins()) {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(admin.getInputStream()));
                    in.readLine();
                } catch (Exception e) {
                    AdminManager.removeAdmin(admin);
                }
            }
        }
    }
}
