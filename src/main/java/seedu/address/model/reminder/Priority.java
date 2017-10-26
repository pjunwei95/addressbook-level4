package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Reminders priority level
 */

public class Priority {

    public static final String PRIORITY_CONSTRAINTS =
            "Priority must have one of the three values which are - High, Low, and Medium";

    private String priority;

    public Priority(String priority) throws IllegalValueException {
        requireNonNull(priority);


        if (!isValidRemark(priority)) {
            throw new IllegalValueException(PRIORITY_CONSTRAINTS);
        }
        this.priority = priority;
    }

    /**
     * Returns true if a given string is a valid Priority.
     */
    public static boolean isValidRemark(String priority) {

        if (!(priority.equalsIgnoreCase("High") || priority.equalsIgnoreCase("Medium")
                || priority.equalsIgnoreCase("Low"))) {

            return false;
        }
        return true;
    }

    /**
     * Get the priority Level of Reminder
     */
    public String getModuleLists() {
        return priority;
    }

    @Override
    public String toString() {
        return priority;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && this.priority.equals(((Priority) other).priority)); // state check
    }

    @Override
    public int hashCode() {
        return priority.hashCode();
    }
}
