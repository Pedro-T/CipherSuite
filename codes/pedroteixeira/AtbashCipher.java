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
        String text = cleanText(input);
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            sb.append(REVERSE_ALPHABET.charAt(ALPHABET.indexOf(text.charAt(i))));
        }
        return sb.toString();
    }

    private static String decrypt(String input) {
        String text = cleanText(input);
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            sb.append(ALPHABET.charAt(REVERSE_ALPHABET.indexOf(text.charAt(i))));
        }
        return sb.toString();
    }
}
