package it.snotix.actions;

import it.snotix.network.PacketUtils;

public class UpdateAction {
    public static void sendAll() {
        PacketUtils.writeToBots("UPDATE");
    }
}
