package codes.pedroteixeira;

import java.util.HashMap;

/**
 * @author Pedro Teixeira
 * @version 4/6/2016
 */
public class MorseCipher extends Cipher {

    private static final HashMap ALPHABET_TO_MORSE = getMorseMap();
    private static final HashMap MORSE_TO_ALPHABET = reverseMap(ALPHABET_TO_MORSE);
    private static String mixedAlphabet = "";
    private static HashMap conversionMap;

    public MorseCipher() {
        super("Fractionated Morse Cipher");
        setKeyField("Key Word: ", KeyField.InputType.TEXT);
        getEncryptButton().setOnAction((event) ->
                getEncryptedTextArea().setText(encrypt(getPlainTextArea().getText(), getKeyField().getText())));
        getDecryptButton().setOnAction((event) ->
                getPlainTextArea().setText(decrypt(getEncryptedTextArea().getText())));
    }

    private static String encrypt(String input, String key) {
        setKey(key);
        String morse = convertToMorse(cleanText(input));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < morse.length() - 2; i += 3) {
            // right now this just converts to morse itself. need to pass it through key word table
        }
        return convertToMorse(input);
    }

    private static String decrypt(String input) {
        return convertFromMorse(input);
    }

    private static String convertToMorse(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            switch(text.charAt(i)) {
                case ' ':
                    sb.append("xx");
                    break;
                default:
                    sb.append(ALPHABET_TO_MORSE.get(text.charAt(i)));
                    sb.append("x");
            }
        }
        return sb.toString();
    }

    private static String convertFromMorse(String text) {
        StringBuilder sb = new StringBuilder();
        String[] words = text.split("xx");
        for (String s : words) {
            String[] letters = s.split("x");
            for (String letterSequence : letters) {
                if (MORSE_TO_ALPHABET.get(letterSequence) != null) {
                    sb.append(MORSE_TO_ALPHABET.get(letterSequence));
                }
            }
            sb.append(" ");
        }
        return sb.toString();
    }

    private static void setKey(String key) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append(ALPHABET.replaceAll(key, ""));
        System.out.println(sb.toString());  // DEBUG ONLY, DELETE THIS
        mixedAlphabet = sb.toString();
    }

    private static HashMap getMorseMap() {
        HashMap map = new HashMap();
        char[] alphabet = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', ',', ':', '"',
                '\'', '!', '?', '@', '-', ';', '(', ')', '=', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', '0'
        };
        String[] morseAlphabet = {
                ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
                ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-",
                ".--", "-..-", "-.--", "--..", ".-.-.-", "--..--", "---...", ".-..-.", ".----.",
                "-.-.--", "..--..", ".--.-.", "-....-", "-.-.-.", "-.--.", "-.--.-", "-....-",
                ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
                "----"
        };
        for (int i = 0; i < morseAlphabet.length; i++) {
            map.put(alphabet[i], morseAlphabet[i]);
        }
        return map;
    }

    private static HashMap reverseMap(HashMap h) {
        HashMap map = new HashMap();
        for (Object o : h.keySet()) {
            map.put(h.get(o), o);
        }
        return map;
    }
}
