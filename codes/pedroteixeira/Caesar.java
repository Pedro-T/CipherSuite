package codes.pedroteixeira;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * This class implements a simple Caesar shift cipher for CipherSuite.
 * @author Pedro Teixeira
 * @version 3/28/2016
 */

public class Caesar extends Cipher {

    private PositiveIntegerField shiftField;

    public Caesar() {
        super("Caesar");
        shiftField = new PositiveIntegerField();
        setIntegerModifierSpace("Shift:", shiftField);
        getEncryptButton().setOnAction((event) ->
                getEncryptedTextArea().setText(encrypt(getPlainTextArea().getText(), shiftField.getValue())));
        getDecryptButton().setOnAction((event) ->
                getPlainTextArea().setText(decrypt(getEncryptedTextArea().getText(), shiftField.getValue())));
    }



    /**
     * This method returns an encrypted string by shifting each character a set number of spaces to the
     * right across the alphabet and adding it to the output string.
     * @param input The string to be encrypted
     * @param shift The number of letters to the right that each character should shift
     * @return A string containing the encrypted sequence
     */
    private String encrypt(String input, int shift) {
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
