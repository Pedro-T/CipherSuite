package codes.pedroteixeira;

/**
 * @author Pedro Teixeira
 * @version 4/14/2016
 */
public class RunningKeyCipher extends Cipher {

    private char[][] tabulaRecta;

    public RunningKeyCipher() {
        super("Running Key Cipher");
        setKeyField("Key:", KeyField.InputType.TEXT);
        getEncryptButton().setOnAction((event) ->
                getEncryptedTextArea().setText(encrypt(getPlainTextArea().getText(), getKeyField().getText())));
        getDecryptButton().setOnAction((event) ->
                getPlainTextArea().setText(decrypt(getEncryptedTextArea().getText(), getKeyField().getText())));
        tabulaRecta = populateTable();
    }

    private String encrypt(String plainText, String keyText) {
        String text = cleanText(plainText);
        String key = cleanText(keyText);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
           sb.append(tabulaRecta[ALPHABET.indexOf(text.charAt(i))][ALPHABET.indexOf(key.charAt(i))]);
        }
        return sb.toString();
    }

    private String decrypt(String encryptedText, String keyText) {
        String text = cleanText(encryptedText);
        String key = cleanText(keyText);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int index = ALPHABET.indexOf(text.charAt(i)) - ALPHABET.indexOf(key.charAt(i));
            if (index < 0) {index += 26;}
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }

    private char[][] populateTable() {
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
