package seedu.address.model.font;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents the font size of the AddressBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidFontSize(String)}
 */
public class FontSize {

    public static final String MESSAGE_FONT_SIZE_CONSTRAINTS =
            "Font size can only be either \"xs\", \"s\","
                    + " \"m\", \"l\",  or \"xl\"";
    public static final String[] FONT_SIZE_LIST = {"xs", "s", "m", "l", "xl"};

    public static final String FONT_SIZE_XS_LABEL = "xs";
    public static final String FONT_SIZE_S_LABEL = "s";
    public static final String FONT_SIZE_M_LABEL = "m";
    public static final String FONT_SIZE_L_LABEL = "l";
    public static final String FONT_SIZE_XL_LABEL = "xl";
    
    public final String value;
    public static String currentFontSizeLabel = FONT_SIZE_M_LABEL;

    /**
     * Validates given font size.
     *
     * @throws IllegalValueException if given font size is invalid.
     */
    public FontSize(String fontSize) throws IllegalValueException {
        requireNonNull(fontSize);
        if (!isValidFontSize(fontSize)) {
            throw new IllegalValueException(MESSAGE_FONT_SIZE_CONSTRAINTS);
        }
        this.value = fontSize;
    }

    /**
     * Returns true if a given string is a valid font size.
     */
    public static boolean isValidFontSize(String test) {
        for (String s : FONT_SIZE_LIST) {
            if (test.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the current font size
     */
    public static String getCurrentFontSizeLabel() {
        return currentFontSizeLabel;
    }

    /**
     * Set the current font size to a new font size
     * @param newFontSizeLabel
     */
    public static void setCurrentFontSizeLabel(String newFontSizeLabel) {
        requireNonNull(newFontSizeLabel);
        currentFontSizeLabel = newFontSizeLabel;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other.equals(this.value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
