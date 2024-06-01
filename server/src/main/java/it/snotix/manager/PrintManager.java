package it.snotix.manager;

import it.snotix.network.PacketUtils;

import java.net.Socket;

public class PrintManager {
    public static void action(String action, Socket request) {
        PacketUtils.writeToSocket(request, "success");
        System.out.println("[Action] " + action + " suggested by " + request);
    }
}
