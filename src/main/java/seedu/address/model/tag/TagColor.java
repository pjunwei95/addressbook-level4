package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

import java.util.Arrays;

/**
 * Represent a color for a tag
 */
public class TagColor {
    public static final String[] VALID_TAG_COLOR = {"red", "blue", "yellow", "green"};
    public static final String MESSAGE_TAG_COLOR_CONSTRAINTS = "Colors names should be one of these: "
            + Arrays.toString(VALID_TAG_COLOR);

    public final String tagColorName;

    /**
     * Validates given tagColor name.
     *
     * @throws IllegalValueException if the given tagColor name string is invalid.
     */
    public TagColor(String name) throws IllegalValueException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!isValidTagColorName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_TAG_COLOR_CONSTRAINTS);
        }
        this.tagColorName = trimmedName;
    }

    /**
     * Returns true if a given string is a valid tagColor name.
     */
    public static boolean isValidTagColorName(String test) {
        return Arrays.asList(VALID_TAG_COLOR).contains(test);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagColor // instanceof handles nulls
                && this.tagColorName.equals(((TagColor) other).tagColorName)); // state check
    }

    @Override
    public int hashCode() {
        return tagColorName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagColorName + ']';
    }
}