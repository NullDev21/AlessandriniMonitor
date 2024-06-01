package it.alessandrini.actions;

import it.alessandrini.network.PacketUtils;

public class ExecAction {
    public static void send(String exec) {
        try {
            String temp;
            int count = 0;

            System.out.print("\n");
            PacketUtils.writeToServer("EXEC " + exec);

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
        } catch (Exception e) {

        }

    }
}
