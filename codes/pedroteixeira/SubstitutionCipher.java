package codes.pedroteixeira;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Pedro Teixeira
 * @version 4/12/2016
 */
public class SubstitutionCipher extends Cipher {

    public SubstitutionCipher() {
        super("Substitution Cipher");
        setKeyField("Key:", KeyField.InputType.TEXT);
        getEncryptButton().setOnAction((event) ->
                getEncryptedTextArea().setText(encrypt(getPlainTextArea().getText(), getKey())));
        getDecryptButton().setOnAction((event) ->
                getPlainTextArea().setText(decrypt(getEncryptedTextArea().getText(), getKey())));
    }

    private String encrypt(String input, String key) {
        String s = cleanText(input);
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(key.charAt(ALPHABET.indexOf(c)));
        }

        return sb.toString();
    }

    private String decrypt(String input, String key) {
        String s = cleanText(input);
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(ALPHABET.charAt(key.indexOf(c)));
        }

        return sb.toString();

    }


    private String getKey() {
        String s = cleanText(getKeyField().getText());
        Set<Character> letters = new HashSet<>();
        for (char c = 'a'; c <= 'z'; c++) {
            letters.add(c);
        }

        for (char c : s.toCharArray()) {
            if (letters.contains(c)) {
                letters.remove(c);
            }
            else {
                getUserMessage().setText("Repeated character in key field. Key field must contain a-z in any order.\n" +
                        "Using default alphabet.");
                return ALPHABET;
            }
        }
        if (letters.isEmpty()) {return s;}
        else {
            getUserMessage().setText("Key field must contain a-z. Using default alphabet.");
            return ALPHABET;
        }
    }
}
