package codes.pedroteixeira;

import javafx.scene.control.TextField;

/**
 * Text field modified to accept only integers
 *
 * Created by Pedro on 3/28/2016.
 */
public class PositiveIntegerField extends TextField {

    PositiveIntegerField() {
        super();
    }

    /**
     * Test replacement text to ensure that only numbers are entered into this text field.
     *
     * @param start start of range to replace
     * @param end end of range to replace
     * @param text text to replace selected range with
     */
    @Override
    public void replaceText(int start, int end, String text) {
        if (isInteger(text)) {
            super.replaceText(start, end, text);
        }
    }

    /**
     *
     * @return content of text field as an int if valid, otherwise 0
     */
    public int getValue() {
        try {
            return Integer.parseInt(super.getText());
        }
        catch (NumberFormatException nfe) {
            return 0;
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
