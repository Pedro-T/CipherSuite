package codes.pedroteixeira;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This class controls the JavaFX application windows, handling all input and passing it on to the classes
 * that implement the various ciphers.
 *
 * @author Pedro Teixeira
 * @version 3/27/2016
 */

public class Main extends Application {

    private boolean running;

    public Main() {
        running = true;
    }

    public void start(Stage primaryStage) {
        HBox menuBar = new HBox();
        menuBar.getChildren().add(new Button("File"));
        menuBar.getChildren().add(new Button("Help"));

        BorderPane border = new BorderPane();
        border.setLeft(createCipherSelector());
        border.setTop(menuBar);
        border.setCenter(createCaesarWorkspace());

        Scene scene = new Scene(border, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CipherSuite 0.1");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private GridPane createCaesarWorkspace() {
        GridPane cipherWorkspace = new GridPane();
        cipherWorkspace.add(new Label("Caesar Cipher"), 1, 1);
        cipherWorkspace.add(new Label("Shift Value:"), 1, 2);
        cipherWorkspace.add(new Label("Input Text:"), 1, 3);
        TextArea plainTextArea = new TextArea();
        cipherWorkspace.add(plainTextArea, 1, 4);
        TextArea encryptedTextArea = new TextArea();
        Button encryptButton = new Button("Encrypt!");
        cipherWorkspace.add(encryptButton, 1, 5);
        Button decryptButton = new Button("Decrypt!");
        cipherWorkspace.add(decryptButton, 2, 5);
        cipherWorkspace.add(encryptedTextArea, 1, 6);

        return cipherWorkspace;
    }

    /**
     * This method generates and returns a vertical-arranged pane of buttons to be used as a cipher selection list.
     * @return A VBox pane of cipher option buttons
     */
    private VBox createCipherSelector() {
        VBox cipherSelectionPane = new VBox();
        cipherSelectionPane.getChildren().add(new Label("Select a Cipher:"));
        cipherSelectionPane.getChildren().add(new Button("Caesar"));
        cipherSelectionPane.getChildren().add(new Button("Rot13"));
        cipherSelectionPane.getChildren().add(new Button("Atbash"));
        cipherSelectionPane.getChildren().add(new Button("Affine"));
        cipherSelectionPane.getChildren().add(new Button("Fractionated Morse"));
        cipherSelectionPane.getChildren().add(new Button("Vigenere"));
        cipherSelectionPane.getChildren().add(new Button("ADFGX"));
        cipherSelectionPane.getChildren().add(new Button("ADFGVX"));
        cipherSelectionPane.getChildren().add(new Button("Rail-Fence"));
        cipherSelectionPane.getChildren().add(new Button("Substitution"));
        cipherSelectionPane.getChildren().add(new Button("Playfair"));
        cipherSelectionPane.getChildren().add(new Button("Running Key"));

        return cipherSelectionPane;
    }

    private HBox createMenuBar() {
        HBox menuBar = new HBox();
        return menuBar;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void run() {
        while (true) {
            launchCipher();
        }
    }

    private void launchCipher() {

    }
}
