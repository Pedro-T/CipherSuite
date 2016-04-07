package codes.pedroteixeira;

/**
 * @author Pedro Teixeira
 * @version 4/7/2016
 */
public class VignereCipher extends Cipher {

    private static final char[][] tabulaRecta = getTableau();

    public VignereCipher() {
        super("Vignere Cipher");
        setKeyField("Shift:", KeyField.InputType.TEXT);
        getEncryptButton().setOnAction((event) ->
                getEncryptedTextArea().setText(encrypt(getPlainTextArea().getText(), getKeyField().getText())));
        getDecryptButton().setOnAction((event) ->
                getPlainTextArea().setText(decrypt(getEncryptedTextArea().getText(), getKeyField().getText())));
    }

    private static String encrypt(String input, String key) {
        String text = cleanText(input);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            sb.append(tabulaRecta[ALPHABET.indexOf(key.charAt(i%key.length()))][ALPHABET.indexOf(c)]);
        }
        return sb.toString();
    }

    private static String decrypt(String input, String key) {
        return input;
    }

    private static char[][] getTableau() {
        char[][] table = new char[26][26];
        int shift = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if(j+shift > 25) {table[i][j] = ALPHABET.charAt(j+shift-26);}
                else {table[i][j] = ALPHABET.charAt(j+shift);}
            }
            shift += 1;
        }
        return table;
    }
}
