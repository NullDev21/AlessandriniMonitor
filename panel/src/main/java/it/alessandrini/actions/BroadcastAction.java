package it.alessandrini.actions;

import it.alessandrini.network.PacketUtils;

public class BroadcastAction {
    public static void sendBroadcast(String msg) {

        PacketUtils.writeToServer("BROADCAST " + msg);

        String temp;
        int count = 0;
        while ((temp = PacketUtils.receivedFromServer()) != null) {
            count++;
            if (count == 10000) {
                System.out.println("Timeout! resend info.");
            }
            if (temp.equals("success")) {
                System.out.println("Operation performed successfully.");
            } else {
                System.out.println("Operation not performed successfully");
            }
            break;
        }
    }
}
