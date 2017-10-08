package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Person's DateOfBirth in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */

public class DateOfBirth {

    public final String Date;
    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Person Date_Of_Birth should not be blank";

    /**
     * Validates DateOfBirth
     *
     * @throws IllegalValueException if given dateOfBirth string is invalid.
     */
    public DateOfBirth(String Date) {

        requireNonNull(Date);
        String trimmedDate = Date.trim();
        this.Date = trimmedDate;
    }
    @Override
    public String toString() { return Date; }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DateOfBirth
                && this.Date.equals(((DateOfBirth)other).Date));
    }
    public static boolean isValidDate(String test) { return test.matches(".*[a-z].*");}

    @Override
    public int hashCode() { return Date.hashCode();}
}
