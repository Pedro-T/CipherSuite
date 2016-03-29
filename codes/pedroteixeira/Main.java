package codes.pedroteixeira;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This class controls the JavaFX application windows, handling all input and passing it on to the classes
 * that implement the various ciphers.
 *
 * @author Pedro Teixeira
 * @version 3/28/2016
 */

public class Main extends Application {

    private CaesarCipher caesarCipher;
    private Rot13Cipher rot13;
    private AtbashCipher atbash;

    private BorderPane mainPane;

    public Main() {
        caesarCipher = new CaesarCipher();
        rot13 = new Rot13Cipher();
        atbash = new AtbashCipher();
        mainPane = new BorderPane();
    }

    public void start(Stage primaryStage) {
        // these are temporary for layout visualization
        HBox menuBar = new HBox();
        menuBar.getChildren().add(new Button("File"));
        menuBar.getChildren().add(new Button("Help"));

        mainPane.setLeft(createCipherSelector());
        mainPane.setTop(menuBar);
        mainPane.setCenter(caesarCipher.getPane());

        Scene scene = new Scene(mainPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CipherSuite 0.1");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    /**
     * This method generates and returns a vertical-arranged pane of buttons to be used as a cipher selection list.
     * @return A VBox pane of cipher option buttons
     */
    public VBox createCipherSelector() {
        VBox cipherSelectionPane = new VBox();
        cipherSelectionPane.getChildren().add(new Label("Select a Cipher:"));
        Button caesarButton = new Button("CaesarCipher");
        caesarButton.setMaxWidth(Double.MAX_VALUE);
        caesarButton.setOnAction((event) -> setWorkspace(caesarCipher.getPane()));
        cipherSelectionPane.getChildren().add(caesarButton);
        Button rot13Button = new Button("Rot13");
        rot13Button.setMaxWidth(Double.MAX_VALUE);
        rot13Button.setOnAction((event) -> setWorkspace(rot13.getPane()));
        cipherSelectionPane.getChildren().add(rot13Button);
        Button atbashButton = new Button("Atbash");
        atbashButton.setMaxWidth(Double.MAX_VALUE);
        atbashButton.setOnAction((event) -> setWorkspace(atbash.getPane()));
        cipherSelectionPane.getChildren().add(atbashButton);
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

    /**
     * Change our view to that of the selected cipher's workspace
     * @param pane Workspace of selected cipher
     */
    private void setWorkspace(GridPane pane) {
        mainPane.setCenter(pane);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
