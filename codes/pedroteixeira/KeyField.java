package codes.pedroteixeira;

import javafx.scene.control.TextField;

/**
 * Text field modified to accept only integers
 *
 * Created by Pedro on 3/28/2016.
 */
public class KeyField extends TextField {

    public enum InputType {
        INTEGER_PAIR,
        INTEGER_SINGLE,
        TEXT
    }

    private InputType type;

    KeyField(InputType type) {
        super();
        this.type = type;
    }

    /**
     * Test replacement text to ensure that only numbers are entered into this text field.
     *
     * @param start start of range to replace
     * @param end   end of range to replace
     * @param text  text to replace selected range with
     */
    @Override
    public void replaceText(int start, int end, String text) {
        switch (type) {
            case INTEGER_PAIR:
                if (text.matches("[0-9,]")) { // this needs work to force int, then comma, then int
                    super.replaceText(start, end, text);
                }
                break;
            case INTEGER_SINGLE:
                if (isInteger(text)) {
                    super.replaceText(start, end, text);
                }
                break;
            case TEXT:
                super.replaceText(start, end, text); // we should refuse special characters? maybe?
                break;
        }
    }

    /**
     * @return content of text field as an int if valid, otherwise 0
     */
    public int getIntegerValue() {
        try {
            return Integer.parseInt(super.getText());
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public KeyPair parseKeyPair() {
        try {
            String[] subs = getText().split(",", 2);
            int valA = Integer.parseInt(subs[0]);
            int valB = Integer.parseInt(subs[1]);
            return new KeyPair(valA,valB);
        }
        catch (NumberFormatException nfe) { //logging function would be productive
            return new KeyPair(-1, -1);
        }
    }

    /**
     * Check if a string is a number
     *
     * @param text The string to check
     * @return whether or not the given string is comprised of numbers
     */
    private boolean isInteger(String text) {
        return text.matches("[0-9]*");
    }

}