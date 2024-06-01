package it.alessandrini;

import it.alessandrini.network.PacketUtils;
import it.alessandrini.security.LicenseChecker;
import javafx.application.Application;
import javafx.stage.Stage;

public class Bootstrap extends Application {
    public static void main(String[] args) {
        PacketUtils.openConnection();
        LicenseChecker.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LicenseChecker licenseChecker = new LicenseChecker();
        licenseChecker.start(primaryStage);
    }
}