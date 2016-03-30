package codes.pedroteixeira;

/**
 * This class implements a simple CaesarCipher shift cipher for CipherSuite.
 * @author Pedro Teixeira
 * @version 3/28/2016
 */

public class CaesarCipher extends Cipher {

    public CaesarCipher() {
        super("CaesarCipher");
        setKeyField("Shift:", KeyField.InputType.INTEGER_SINGLE);
        getEncryptButton().setOnAction((event) ->
                getEncryptedTextArea().setText(encrypt(getPlainTextArea().getText(), getKeyField().getIntegerValue())));
        getDecryptButton().setOnAction((event) ->
                getPlainTextArea().setText(decrypt(getEncryptedTextArea().getText(), getKeyField().getIntegerValue())));
    }

    /**
     * This method returns an encrypted string by shifting each character a set number of spaces to the
     * right across the alphabet and adding it to the output string.
     * @param input The string to be encrypted
     * @param shift The number of letters to the right that each character should shift
     * @return A string containing the encrypted sequence
     */
    private static String encrypt(String input, int shift) {
        String text = cleanText(input);
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            sb.append(ALPHABET.charAt((ALPHABET.indexOf(text.charAt(i)) + shift)%26));
        }
        return sb.toString();
    }

    /**
     * This method returns a decrypted string by shifting each character a set number of spaces to the
     * left across the alphabet and adding it to the output string.
     * @param input The string to be decrypted
     * @param shift The number of letters to the right that each character should shift
     * @return A string containing the encrypted sequence
     */
    private static String decrypt(String input, int shift) {
        String text = cleanText(input);
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            int charIndex = ALPHABET.indexOf(text.charAt(i));
            int outChar = (charIndex - shift)%ALPHABET.length();
            if (outChar < 0) {
                outChar += ALPHABET.length();
            }
            sb.append(ALPHABET.charAt(outChar));
        }
        return sb.toString();
    }
}
