package codes.pedroteixeira;

import java.util.HashMap;

/**
 * @author Pedro Teixeira
 * @version 4/7/2016
 */
public class MorseCipher extends Cipher {

    private static final HashMap ALPHABET_TO_MORSE;
    private static final HashMap MORSE_TO_ALPHABET;

    static {
        ALPHABET_TO_MORSE = getMorseMap();
        MORSE_TO_ALPHABET = reverseMap(ALPHABET_TO_MORSE);
    }


    public MorseCipher() {
        super("Fractionated Morse Cipher");
        setKeyField("Key Word: ", KeyField.InputType.TEXT);
        getEncryptButton().setOnAction((event) ->
                getEncryptedTextArea().setText(encrypt(getPlainTextArea().getText(), getKeyField().getText())));
        getDecryptButton().setOnAction((event) ->
                getPlainTextArea().setText(decrypt(getEncryptedTextArea().getText(), getKeyField().getText())));
    }

    private String encrypt(String input, String key) {
        HashMap conversionMap = getKeyConvertMap(getKeyAlphabet(key));
        String morse = convertToMorse(input);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < morse.length() - 2; i += 3) {
            sb.append(conversionMap.get(morse.substring(i, i+3)));
        }
        return sb.toString();
    }

    private String decrypt(String input, String key) {
        HashMap conversionMap = reverseMap(getKeyConvertMap(getKeyAlphabet(key)));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            sb.append(conversionMap.get(input.charAt(i)));
        }
        return convertFromMorse(sb.toString());
    }

    private static String convertToMorse(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                sb.append("x");
            }
            else {
                if(ALPHABET_TO_MORSE.get(c) != null) {
                    sb.append(ALPHABET_TO_MORSE.get(c));
                    sb.append("x");
                }
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

    private static String getKeyAlphabet(String key) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for (char c : ALPHABET.toCharArray()) {
            if (key.indexOf(c) == -1) {
                sb.append(c);
            }
        }

        System.out.println(sb.toString());  // DEBUG ONLY, DELETE THIS
        return sb.toString();
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

    private HashMap getKeyConvertMap(String key) {
        HashMap map = new HashMap();
        String[] conversionTable = {
                "...", "..-", "..x", ".-.", ".--", ".-x", ".x.", ".x-", ".xx", "-..", "-.-",
                "-.x", "--.", "---", "--x", "-x.", "-x-", "-xx", "x..", "x.-", "x.x", "x-.",
                "x--", "x-x", "xx.", "xx-"
        };
        for (int i = 0; i < conversionTable.length; i++) {
            map.put(conversionTable[i], key.charAt(i));
        }
        return map;
    }
}
