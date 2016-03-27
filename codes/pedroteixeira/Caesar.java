package codes.pedroteixeira;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * This class implements a simple Caesar shift cipher for CipherSuite.
 * @author Pedro Teixeira
 * @version 3/27/2016
 */

public class Caesar {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private GridPane cipherWorkspace;

    Caesar() {
        cipherWorkspace = new GridPane();
        generateScene();
    }

    private void generateScene() {
        cipherWorkspace.add(new Label("Caesar Cipher"), 1, 1);
        cipherWorkspace.add(new Label("Shift Value:"), 1, 2);
        cipherWorkspace.add(new Label("Input Text:"), 1, 3);
        TextArea plainTextArea = new TextArea();
        ComboBox shiftSelector = new ComboBox();
        for (int i = 0; i <= 26; i++) {
            shiftSelector.getItems().add(i);
        }
        cipherWorkspace.add(shiftSelector, 2, 2);
        cipherWorkspace.add(plainTextArea, 1, 4);
        TextArea encryptedTextArea = new TextArea();
        Button encryptButton = new Button("Encrypt!");
        encryptButton.setOnAction((event) ->
                encryptedTextArea.setText(encrypt(plainTextArea.getText(), 4)));
        cipherWorkspace.add(encryptButton, 1, 5);
        Button decryptButton = new Button("Decrypt!");
        cipherWorkspace.add(decryptButton, 2, 5);
        cipherWorkspace.add(encryptedTextArea, 1, 6);
    }

    public GridPane getPane() {
        return cipherWorkspace;
    }

    /**
     * This method returns an encrypted string by shifting each character a set number of spaces to the
     * right across the alphabet and adding it to the output string.
     * @param input The string to be encrypted
     * @param shift The number of letters to the right that each character should shift
     * @return A string containing the encrypted sequence
     */
    private static String encrypt(String input, int shift) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output += ALPHABET.charAt((ALPHABET.indexOf(input.charAt(i)) + shift)%26);
        }
        return output;
    }

    /**
     * This method returns a decrypted string by shifting each character a set number of spaces to the
     * left across the alphabet and adding it to the output string.
     * @param input The string to be decrypted
     * @param shift The number of letters to the right that each character should shift
     * @return A string containing the encrypted sequence
     */
    private static String decrypt(String input, int shift) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            int charIndex = ALPHABET.indexOf(Character.toLowerCase(input.charAt(i)));
            int outChar = (charIndex - shift)%ALPHABET.length();
            if (outChar < 0) {
                outChar += ALPHABET.length();
            }

            output += ALPHABET.charAt(outChar);
        }
        return output;
    }
}
