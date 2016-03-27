package codes.pedroteixeira;

/**
 * This class implements a simple Caesar shift cipher for CipherSuite.
 * @author Pedro Teixeira
 * @version 3/27/2016
 */

public class Caesar {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    Caesar() {

    }

    /**
     * This method returns an encrypted string by shifting each character a set number of spaces to the
     * right across the alphabet and adding it to the output string.
     * @param input The string to be encrypted
     * @param shift The number of letters to the right that each character should shift
     * @return A string containing the encrypted sequence
     */
    public static String encrypt(String input, int shift) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output += ALPHABET.charAt((ALPHABET.indexOf(input) + shift)%25);
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
    public static String decrypt(String input, int shift) {
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
