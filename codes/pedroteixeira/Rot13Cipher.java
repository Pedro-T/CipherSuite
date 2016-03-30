package codes.pedroteixeira;

/**
 * This class implements a simple CaesarCipher shift cipher for CipherSuite.
 * @author Pedro Teixeira
 * @version 3/28/2016
 */

public class Rot13Cipher extends Cipher {

    private KeyField shiftField;

    public Rot13Cipher() {
        super("Rot13");
        getEncryptButton().setOnAction((event) ->
                getEncryptedTextArea().setText(encrypt(getPlainTextArea().getText())));
        getDecryptButton().setOnAction((event) ->
                getPlainTextArea().setText(decrypt(getEncryptedTextArea().getText())));
    }

    /**
     * This method returns an encrypted string by shifting each character a set number of spaces to the
     * right across the alphabet and adding it to the output string.
     * @param input The string to be encrypted
     * @return A string containing the encrypted sequence
     */
    private static String encrypt(String input) {
        String text = cleanText(input);
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            sb.append(ALPHABET.charAt((ALPHABET.indexOf(text.charAt(i)) + 13)%26));
        }
        return sb.toString();
    }

    /**
     * This method returns a decrypted string by shifting each character a set number of spaces to the
     * left across the alphabet and adding it to the output string.
     * @param input The string to be decrypted
     * @return A string containing the encrypted sequence
     */
    private static String decrypt(String input) {
        String text = cleanText(input);
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            int charIndex = ALPHABET.indexOf(Character.toLowerCase(text.charAt(i)));
            int outChar = (charIndex - 13)%ALPHABET.length();
            if (outChar < 0) {
                outChar += ALPHABET.length();
            }

            sb.append(ALPHABET.charAt(outChar));
        }
        return sb.toString();
    }
}