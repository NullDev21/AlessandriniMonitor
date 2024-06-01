package it.snotix.actions;

import it.snotix.network.PacketUtils;

public class BroadcastAction {
    public static void sendAll(String message) {
        String messToBroad;
        messToBroad = message.replace("BROADCAST", " ");
        messToBroad.trim();

        PacketUtils.writeToBots("BROADCAST " + messToBroad);
    }
}
