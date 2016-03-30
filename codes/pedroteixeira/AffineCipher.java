package codes.pedroteixeira;

/**
 * @author Pedro Teixeira
 * @version 3/29/2016
 */
public class AffineCipher extends Cipher{

    public AffineCipher() {
        super("Affine");
        setKeyField("Shift:", KeyField.InputType.INTEGER_PAIR);
        getEncryptButton().setOnAction((event) ->
                getEncryptedTextArea().setText(encrypt(getPlainTextArea().getText(), getKeyField().parseKeyPair())));
        getDecryptButton().setOnAction((event) ->
                getPlainTextArea().setText(decrypt(getEncryptedTextArea().getText(), getKeyField().parseKeyPair())));
    }

    public String encrypt(String input, KeyPair pair) {
        String text = cleanText(input);
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            sb.append(ALPHABET.charAt((pair.a() * ALPHABET.indexOf(text.charAt(i)) + pair.b())%26));
        }
        return sb.toString();
    }

    public String decrypt(String input, KeyPair pair) {
        String text = cleanText(input);
        StringBuilder sb = new StringBuilder(text.length());
        if (getInverse(pair.a()) == -1) {
            return "Error: No inverse exists for " + pair.a() + ", invalid key.";
        }
        else
        {
            for (int i = 0; i < text.length(); i++) {
                int x = (getInverse(pair.a()) * (ALPHABET.indexOf(text.charAt(i)) - pair.b())%26);
                if (x < 0) {x += 26;}
                sb.append(ALPHABET.charAt(x));
            }
        }
        return sb.toString();
    }

    private int getInverse(int x) {
        for (int i = 1; i <= 26; i++) {
            if (((x * i)%26) == 1) {
                return i;
            }
        }
        return -1;
    }
}
