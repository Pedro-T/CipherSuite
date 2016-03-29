package codes.pedroteixeira;

/**
 * This class implements the Atbash substitution cipher
 * Created by Pedro on 3/28/2016.
 */
public class AtbashCipher extends Cipher {

    private static final String REVERSE_ALPHABET = "zyxwvutsrqponmlkjihgfedcba";

    public AtbashCipher() {
        super("Atbash");
        getEncryptButton().setOnAction((event) ->
                getEncryptedTextArea().setText(encrypt(getPlainTextArea().getText())));
        getDecryptButton().setOnAction((event) ->
                getPlainTextArea().setText(decrypt(getEncryptedTextArea().getText())));
    }

    private static String encrypt(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output += REVERSE_ALPHABET.charAt(ALPHABET.indexOf(input.charAt(i)));
        }
        return output;
    }

    private static String decrypt(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output += ALPHABET.charAt(REVERSE_ALPHABET.indexOf(input.charAt(i)));
        }
        return output;
    }
}
