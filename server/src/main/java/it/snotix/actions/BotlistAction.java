package it.snotix.actions;

import it.snotix.manager.AdminManager;
import it.snotix.manager.BotManager;
import it.snotix.network.PacketUtils;

import java.net.Socket;

public class BotlistAction {
    public static void sendList (Socket socket) {
        // _back_ == \n for client. idk is bugged but, my system is that XD

        PacketUtils.writeToSocket(socket, "LIST " + "_back__back_Total bots: " + BotManager.getBots().size());
    }
}
