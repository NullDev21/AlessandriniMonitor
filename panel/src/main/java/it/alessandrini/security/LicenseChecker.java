package it.alessandrini.security;

import it.alessandrini.network.PacketUtils;
import it.alessandrini.panel.PanelMainGUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LicenseChecker extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("License Checker");

        Label licenseLabel = new Label("Inserisci licenza:");
        TextField licenseField = new TextField();
        Button submitButton = new Button("Submit");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(licenseLabel, licenseField, submitButton);

        submitButton.setOnAction(e -> {
            String license = licenseField.getText().trim();
            if (license.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Empty License", "Please enter a license.");
            } else {
                boolean validLicense = validateLicense(license);
                if (validLicense) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Successfully logged!", null);
                    new PanelMainGUI().start(primaryStage);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Wrong License", "Please enter a valid license.");
                }
            }
        });

        Scene scene = new Scene(vbox, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validateLicense(String license) {
        PacketUtils.writeToServer("LOGIN_REQUEST " + license);
        String response = PacketUtils.receivedFromServer();
        return response != null && response.equals("true");
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
