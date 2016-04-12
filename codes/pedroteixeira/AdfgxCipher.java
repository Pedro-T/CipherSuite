package codes.pedroteixeira;

import java.util.Arrays;

/**
 * @author Pedro Teixeira
 * @version 4/12/2016
 */
public class AdfgxCipher extends Cipher {

    private char[] letters = { 'a', 'd', 'f', 'g', 'x'};

    public AdfgxCipher() {
        super("ADFGX Cipher");
        setKeyField("Key:", KeyField.InputType.TEXT);
        getEncryptButton().setOnAction((event) ->
                getEncryptedTextArea().setText(encrypt(getPlainTextArea().getText(), getKey(), getKeySquare())));
        getDecryptButton().setOnAction((event) ->
                getPlainTextArea().setText(decrypt(getEncryptedTextArea().getText(), getKey(), getKeySquare())));
    }

    private String encrypt(String input, String key, String keySquare) {
        String text = cleanText(input);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(letters[keySquare.indexOf(text.charAt(i))%5]);
            if (keySquare.indexOf(text.charAt(i)) < 5) { sb.append(letters[0]);}
            else if (keySquare.indexOf(text.charAt(i)) >= 5 && keySquare.indexOf(text.charAt(i)) < 10) { sb.append(letters[1]);}
            else if (keySquare.indexOf(text.charAt(i)) >= 10 && keySquare.indexOf(text.charAt(i)) < 15) { sb.append(letters[2]);}
            else if (keySquare.indexOf(text.charAt(i)) >= 15 && keySquare.indexOf(text.charAt(i)) < 20) { sb.append(letters[3]);}
            else if (keySquare.indexOf(text.charAt(i)) >= 20 && keySquare.indexOf(text.charAt(i)) < 25) { sb.append(letters[4]);}
            //sb.append(' '); // I don't think this is needed at this point
        }
        return transpose(sb.toString(), key);
    }

    private String decrypt(String text, String key, String keySquare) {
        return text;
    }

    private String transpose(String s, String keyText) {
        char[] key = keyText.toCharArray();
        char[] text = s.toCharArray();

        HeaderSortArray[] columns = new HeaderSortArray[key.length];
        int[] sizes = new int[key.length];

        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = text.length / key.length;
            if (i < (text.length - sizes[i] * key.length)) {sizes[i]++;}
            columns[i] = new HeaderSortArray(sizes[i], key[i]);
        }
        int x = 0;
        for (char c : text) {
            System.out.println("X=" +x);
            System.out.println(columns[x].getLength());
            columns[x].addChar(c);
            x += 1;
            x %= columns.length;
        }
        Arrays.sort(columns);
        StringBuilder sb = new StringBuilder();
        for (HeaderSortArray h : columns) {
            sb.append(h.getText());
        }
        return sb.toString();
    }


    private String getKey() {
        String s = getKeyField().getText();
        if (s.indexOf(',') != -1) {
            s = s.substring(0, s.indexOf(','));
        }
        return cleanText(s);
    }

    private String getKeySquare() {
        String s = getKeyField().getText();
        if (s.indexOf(',') != -1) {
            s = s.substring(s.indexOf(',')+1, s.length());
        }
        System.out.println("KS length " + s.length());
        if (s.length() == 25) {return s;}
        else {
            getUserMessage().setText("Error: Key square length must be 25! Used default square.");
            return "abcdefghiklmnopqrstuvwxyz";
        }
    }

    private class HeaderSortArray implements Comparable<HeaderSortArray> {

        private char indexChar;
        private char[] letterList;
        private int arrayIndex;

        public HeaderSortArray(int size, char c) {
            letterList = new char[size];
            indexChar = c;
            arrayIndex = 0;
        }

        void addChar(char c) {
            letterList[arrayIndex] = c;
            arrayIndex++;
        }

        public String getText() {
            return new String(letterList);
        }

        public int compareTo(HeaderSortArray h) {
            return this.indexChar - h.indexChar;
        }

        int getLength() {
            return letterList.length;
        }

        public char getIndexChar() {
            return indexChar;
        }
    }
}
