package it.snotix.client.actions;


public class actionManager {
    public static void searchAction(String action) {
        if (action.startsWith("BROADCAST")) {
            broadcastAction(action);
        }

        if (action.startsWith("EXEC")) {
            execAction(action);
        }

        if (action.startsWith("SHUTDOWN")) {
            shutdown();
        }

        if (action.startsWith("UPDATE")) {
            updateAction();
        }
    }

    private static void broadcastAction(String message) {
        String messToBroad;
        messToBroad = message.replace("BROADCAST", " ");
        messToBroad.trim();
        try {
            Runtime.getRuntime().exec("powershell -command \"[System.Reflection.Assembly]::LoadWithPartialName('PresentationFramework'); [System.Windows.MessageBox]::Show('" + messToBroad +"', 'ALESSANDRINI NETWORK BROADCAST SYSTEM')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void execAction(String message) {
        String exec;
        exec = message.replace("EXEC", " ").trim();
        try {
            Runtime.getRuntime().exec(exec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // now update is making by UrlClassLoader
    private static void updateAction() {
        System.exit(-2);
    }

    private static void shutdown() {
        try {
         Runtime.getRuntime().exec("cmd.exe /c shutdown -s -t 120");
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
}
