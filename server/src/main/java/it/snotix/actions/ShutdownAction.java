package it.snotix.actions;

import it.snotix.manager.PrintManager;
import it.snotix.network.PacketUtils;

public class ShutdownAction {
    public static void sendALL() {
        PacketUtils.writeToBots("SHUTDOWN");
    }
}
