package codes.pedroteixeira;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Implements a basic user interface for cipher operations
 *
 * Created by Pedro on 3/28/2016.
 */
public class Cipher {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private HBox modifierBar = new HBox();
    private Button decryptButton;
    private Button encryptButton;
    private GridPane cipherWorkspace = new GridPane();
    private String cipherName;
    private TextArea plainTextArea = new TextArea();
    private TextArea encryptedTextArea = new TextArea();
    private HBox actionButtonBar;

    public Cipher(String name) {
        cipherName = name;
        buildPane();
    }

    private void buildPane() {
        cipherWorkspace.add(new Label(cipherName), 1, 1, 1, 1);
        cipherWorkspace.add(new Label("Shift Value:"), 1, 2, 1, 1);
        cipherWorkspace.add(new Label("Input Text:"), 1, 4);
        cipherWorkspace.add(plainTextArea, 1, 4);
        buildActionButtons();
        cipherWorkspace.add(actionButtonBar, 1, 5);
        cipherWorkspace.add(encryptedTextArea, 1, 7);
    }

    private void buildActionButtons() {
        encryptButton = new Button("Encrypt!");
        decryptButton = new Button("Decrypt!");
        actionButtonBar = new HBox();
        encryptButton.setMaxWidth(Double.MAX_VALUE);
        decryptButton.setMaxWidth(Double.MAX_VALUE);
        actionButtonBar.setHgrow(encryptButton, Priority.ALWAYS);
        actionButtonBar.setHgrow(decryptButton, Priority.ALWAYS);
        actionButtonBar.getChildren().addAll(encryptButton, decryptButton);
    }

    /**
     * Adds an integer-only field to the cipher workspace
     * @param labelText Text for the field description
     * @param field a PositiveIntegerField to add
     */
    public void setIntegerModifierSpace(String labelText, PositiveIntegerField field) {
        modifierBar.getChildren().addAll(new Label(labelText), field);
        cipherWorkspace.add(modifierBar, 1, 3);
    }

    public GridPane getPane() {
        return cipherWorkspace;
    }

    public Button getEncryptButton() {
        return encryptButton;
    }

    public Button getDecryptButton() {
        return decryptButton;
    }

    public TextArea getPlainTextArea() {
        return plainTextArea;
    }

    public TextArea getEncryptedTextArea() {
        return encryptedTextArea;
    }


}
