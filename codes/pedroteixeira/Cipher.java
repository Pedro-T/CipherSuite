package codes.pedroteixeira;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Implements a basic user interface for cipher operations
 *
 * @author Pedro Teixeira
 * @version 4/12/2016
 */
public class Cipher {

    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private HBox modifierBar = new HBox();
    private Button decryptButton;
    private Button encryptButton;
    private GridPane cipherWorkspace = new GridPane();
    private String cipherName;
    private TextArea plainTextArea = new TextArea();
    private TextArea encryptedTextArea = new TextArea();
    private Label userMessage = new Label();
    private HBox actionButtonBar;
    private KeyField keyField;

    Cipher(String name) {
        cipherName = name;
        buildPane();
    }

    /**
     * Removes all non number or letter characters
     * @param text Text to be modified
     * @return Modified string without characters
     */
    static String cleanText(String text) {
        return text.replaceAll("[^\\w]", "").replaceAll("[\\s]", "").toLowerCase();
    }

    /**
     * Applies common controls to pane
     */
    private void buildPane() {
        cipherWorkspace.add(new Label(cipherName), 1, 1, 1, 1);
        cipherWorkspace.add(new Label("Input Text:"), 1, 4);
        cipherWorkspace.add(plainTextArea, 1, 4);
        buildActionButtons();
        cipherWorkspace.add(actionButtonBar, 1, 5);
        cipherWorkspace.add(encryptedTextArea, 1, 6);
        cipherWorkspace.add(userMessage, 1, 7);
    }

    /**
     * Creates and applies encrypt and decrypt button area
     */
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
     * @param type the type of KeyField to use
     */
    void setKeyField(String labelText, KeyField.InputType type) {
        keyField = new KeyField(type);
        modifierBar.getChildren().addAll(new Label(labelText), keyField);
        cipherWorkspace.add(modifierBar, 1, 3);
    }

    Label getUserMessage() {return userMessage;}

    GridPane getPane() {
        return cipherWorkspace;
    }

    Button getEncryptButton() {
        return encryptButton;
    }

    Button getDecryptButton() {
        return decryptButton;
    }

    TextArea getPlainTextArea() {
        return plainTextArea;
    }

    TextArea getEncryptedTextArea() {
        return encryptedTextArea;
    }

    KeyField getKeyField() { return keyField; }


}
