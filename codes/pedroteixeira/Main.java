package codes.pedroteixeira;

import javafx.application.Application;
import javafx.geometry.Insets;
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
 * @version 4/12/2016
 */

public class Main extends Application {

    private CaesarCipher caesarCipher;
    private Rot13Cipher rot13;
    private AtbashCipher atbash;
    private AffineCipher affine;
    private MorseCipher morse;
    private VignereCipher vignere;
    private AdfgxCipher adfgx;

    private BorderPane mainPane;

    public Main() {
        caesarCipher = new CaesarCipher();
        rot13 = new Rot13Cipher();
        atbash = new AtbashCipher();
        affine = new AffineCipher();
        morse = new MorseCipher();
        vignere = new VignereCipher();
        adfgx = new AdfgxCipher();

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
     * This method generates and returns a vertical-arranged pane of buttons to be used as a
     * cipher selection list.
     * @return A VBox pane of cipher option buttons
     */
    private VBox createCipherSelector() {
        VBox cipherSelectionPane = new VBox();
        cipherSelectionPane.setPadding(new Insets(10, 10, 10, 10));
        cipherSelectionPane.getStylesheets().add(getClass().getResource("CipherSuite.css").toExternalForm());
        Label name = new Label("Select a Cipher");
        name.setMaxWidth(Double.MAX_VALUE);
        Button caesarButton = new Button("Caesar");
        caesarButton.setMaxWidth(Double.MAX_VALUE);
        caesarButton.setOnAction((event) -> setWorkspace(caesarCipher.getPane()));
        Button rot13Button = new Button("Rot13");
        rot13Button.setMaxWidth(Double.MAX_VALUE);
        rot13Button.setOnAction((event) -> setWorkspace(rot13.getPane()));
        Button atbashButton = new Button("Atbash");
        atbashButton.setMaxWidth(Double.MAX_VALUE);
        atbashButton.setOnAction((event) -> setWorkspace(atbash.getPane()));
        Button affineButton = new Button("Affine");
        affineButton.setMaxWidth(Double.MAX_VALUE);
        affineButton.setOnAction((event) -> setWorkspace(affine.getPane()));
        Button morseButton = new Button("Fractionated Morse");
        morseButton.setMaxWidth(Double.MAX_VALUE);
        morseButton.setOnAction((event) -> setWorkspace(morse.getPane()));
        Button vignereButton = new Button("Vignere");
        vignereButton.setMaxWidth(Double.MAX_VALUE);
        vignereButton.setOnAction((event) -> setWorkspace(vignere.getPane()));
        Button adfgxButton = new Button("ADFGX");
        adfgxButton.setMaxWidth(Double.MAX_VALUE);
        adfgxButton.setOnAction((event) -> setWorkspace(adfgx.getPane()));
        Button subButton = new Button("Substitution");
        subButton.setMaxWidth(Double.MAX_VALUE);
        //subButton.setOnAction((event) -> setWorkspace(substitution.getPane()));
        Button runningKeyButton = new Button("Running Key");
        runningKeyButton.setMaxWidth(Double.MAX_VALUE);
        //runningKeyButton.setOnAction((event) -> setWorkspace(runningKey.getPane()));

        cipherSelectionPane.getChildren().addAll(name, caesarButton, rot13Button, atbashButton,
                affineButton, morseButton, vignereButton, adfgxButton, subButton, runningKeyButton);

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
