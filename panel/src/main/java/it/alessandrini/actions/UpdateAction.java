package it.alessandrini.actions;

import it.alessandrini.network.PacketUtils;

public class UpdateAction {
    public static void send() {
        PacketUtils.writeToServer("UPDATE");

        String temp;
        while ((temp = PacketUtils.receivedFromServer()) != null) {
            if (temp.equals("success")) {
                System.out.println("Operation performed successfully.");
            } else {
                System.out.println("Operation not performed successfully");
            }
            break;
        }
    }
}
