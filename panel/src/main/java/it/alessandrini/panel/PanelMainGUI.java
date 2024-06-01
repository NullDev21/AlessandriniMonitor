package it.alessandrini.panel;

import it.alessandrini.actions.*;
import it.alessandrini.network.PacketUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PanelMainGUI extends Application {

    private final TextArea outputArea = new TextArea();
    private final TextField inputField = new TextField();
    static final String CREDITS = "\nSoftware ideato e sviluppato da Maikol R. durante il terzo anno (2023-2024)\n";

    static String[] Commands = {"help", "clear", "credits", "list", "broadcast", "exec", "shutdown", "update"};

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Alessandrini Monitor");

        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setStyle("-fx-font-family: 'Courier New', Courier, monospace;");

        inputField.setOnAction(e -> processInput());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        MenuBar menuBar = createMenuBar();
        HBox header = createHeader();
        HBox commandButtons = createCommandButtons();

        layout.getChildren().addAll(menuBar, header, outputArea, inputField, commandButtons);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        displayASCII();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().add(exitMenuItem);

        menuBar.getMenus().add(fileMenu);

        return menuBar;
    }

    private HBox createHeader() {
        HBox header = new HBox(10);
        header.setAlignment(javafx.geometry.Pos.CENTER);
        Label titleLabel = new Label("Alessandrini Monitor");
        titleLabel.setFont(new Font("Arial", 24));
        titleLabel.setStyle("-fx-text-fill: #333;");
        header.getChildren().addAll(titleLabel);
        return header;
    }

    private HBox createCommandButtons() {
        HBox commandButtons = new HBox(10);
        commandButtons.setAlignment(javafx.geometry.Pos.CENTER);
        commandButtons.setPadding(new Insets(10));

        for (String command : Commands) {
            Button button = new Button(command.toUpperCase());
            button.setMinWidth(80);
            button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
            button.setOnAction(e -> handleCommand(command));
            Tooltip tooltip = new Tooltip(command.toUpperCase());
            button.setTooltip(tooltip);
            commandButtons.getChildren().add(button);
        }

        return commandButtons;
    }

    private void displayASCII() {
        outputArea.appendText("Benvenuto, ora hai il controllo di tutti i dispositivi scolastici, fanne buon uso.\n");
    }

    private void processInput() {
        String action = inputField.getText().trim().toLowerCase();
        inputField.clear();
        handleCommand(action);
    }

    private void handleCommand(String action) {
        switch (action) {
            case "clear":
                outputArea.clear();
                break;

            case "credits":
                outputArea.appendText(CREDITS);
                break;

            case "help":
                outputArea.appendText("\nTUTTI I COMANDI: \n");
                for (String command : Commands) {
                    outputArea.appendText(command.toUpperCase() + "\n");
                }
                outputArea.appendText("\n");
                break;

            case "broadcast":
                TextInputDialog broadcastDialog = new TextInputDialog();
                broadcastDialog.setTitle("Messaggio di brodcast");
                broadcastDialog.setHeaderText(null);
                broadcastDialog.setContentText("Inserisci messaggio di brodcast:");
                broadcastDialog.showAndWait().ifPresent(message -> BroadcastAction.sendBroadcast(message));
                outputArea.appendText("Azione di brodcast inviata al server.");
                break;

            case "exec":
                TextInputDialog execDialog = new TextInputDialog();
                execDialog.setTitle("Esegui comandi");
                execDialog.setHeaderText(null);
                execDialog.setContentText("Inserisci comandi da eseguire su tutti i computer:");
                execDialog.showAndWait().ifPresent(command -> ExecAction.send(command));
                outputArea.appendText("Azzione di execute inviata al server");
                break;

            case "list":
                PacketUtils.writeToServer("LIST");

                String list;

                while ((list = PacketUtils.receivedFromServer()) != null) {
                    list = list.replace("LIST", "").trim();
                    list = list.replace("_back_", "\n");
                    outputArea.appendText(list);
                    break;
                }
                break;

            case "shutdown":
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Conferma");
                alert.setHeaderText("Shutdown di tutti i computer");
                alert.setContentText("ne sei proprio sicuro?");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        PacketUtils.writeToServer("SHUTDOWN");
                    }
                });
                break;

            case "update":
                UpdateAction.send();
                break;

            default:
                outputArea.appendText("Comando non trovato, scrivi 'help' per avere la lista dei comandi.\n");
        }
    }
}
