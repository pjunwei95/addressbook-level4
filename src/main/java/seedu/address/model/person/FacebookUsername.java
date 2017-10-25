package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Person's username on Facebook
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class FacebookUsername {

    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Person usernames should be the username of the person on Facebook";

    /*
     * The first character of the username must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String username;

    /**
     * Validates given username
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public FacebookUsername(String username) throws IllegalValueException {
        requireNonNull(username);
        String trimmedName = username.trim();
        if (!isValidName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.username = trimmedName;
    }

    /**
     * Returns true if a given string is a valid person name.
     */
    public static boolean isValidName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.username.equals(((FacebookUsername) other).username)); // state check
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

}

