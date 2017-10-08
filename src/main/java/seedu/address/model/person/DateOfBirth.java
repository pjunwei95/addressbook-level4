package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Person's DateOfBirth in the address book.
 */

public class DateOfBirth {

    public final String Date;

    public DateOfBirth(String Date) {
        String trimmedDate = Date.trim();
        this.Date = Date;
    }
    @Override
    public String toString() { return Date; }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DateOfBirth
                && this.Date.equals(((DateOfBirth)other).Date));
    }

    @Override
    public int hashCode() { return Date.hashCode();}
}
