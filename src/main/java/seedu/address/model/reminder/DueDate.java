package seedu.address.model.reminder;
//@@author RonakLakhotia
import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Reminders DueDate in the address book.
 */

public class DueDate {

    /**
     * Represents a Reminders DueDate
     * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
     */

    public static final String DUE_DATE_VALIDATION_REGEX = "(0[1-9]|[1-9]|1[0-9]|2[0-9]|3[01])[///./-]"
            + "(0[1-9]|1[0-2]|[1-9])[///./-](19|20)[0-9][0-9]";


    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Due Date must be a Valid Date and in the following format: \n"
                    + "'.' and '/' can be used as separators. \n";

    public final String date;
    public static final int lastIndex = 1;
    public static final int firstIndex = 0;
    public static final int FEBRUARY = 2;
    public static final int DAYS_IN_FEBRUARY = 28;

    public DueDate(String date) throws IllegalValueException {

        String trimmedDate;
        if (!isValidDate(date)) {
            throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
        }
        if (!date.equals("")) {
            trimmedDate = date.trim();
            this.date = trimmedDate;
        } else {
            this.date = "";
        }

    }
    /**
     * Returns true if a given string is a valid person birthday.
     */
    public static boolean isValidDate(String dueDate) {

        String trimmedDate = dueDate.trim();
        if (trimmedDate.isEmpty()) {
            return false;
        }
        if (!trimmedDate.matches(DUE_DATE_VALIDATION_REGEX)) {
            return false;
        }
        boolean isValidDate = checkIfValidDate(dueDate);

        return isValidDate;

    }

    public static boolean checkIfValidDate(String date) {

        int dayNumber, monthNumber, yearNumber;
        boolean isLeapYear, isValidDateOfFebruary;

        int lastIndexOfSeparator = getIndexOfSeparator(date, lastIndex);
        int firstIndexOfSeparator = getIndexOfSeparator(date, firstIndex);
        yearNumber = getYearNumber(lastIndexOfSeparator, date);
        monthNumber = getMonthNumber(firstIndexOfSeparator, lastIndexOfSeparator, date);
        isLeapYear = checkIfLeapYear(yearNumber);
        dayNumber = getDayNumber(firstIndexOfSeparator, date);
        isValidDateOfFebruary = checkIfValidDateOfFebruary(date, monthNumber, yearNumber, dayNumber, isLeapYear);

        if (isValidDateOfFebruary) {
            return true;
        }
        return false;

    }
    public static boolean checkIfValidDateOfFebruary(String date, int month, int year, int day, boolean isLeapYear) {

        if (month == FEBRUARY) {

            if (isLeapYear && day > (DAYS_IN_FEBRUARY + 1) || (!isLeapYear && day > DAYS_IN_FEBRUARY)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIfLeapYear(int yearNumber) {

        if (yearNumber % 400 == 0 || (yearNumber % 4 == 0 && yearNumber % 100 != 0)) {
            return true;
        }
        return false;
    }

    public static int getYearNumber(int lastIndexOfSeparator, String date) {

        String year = date.substring(lastIndexOfSeparator + 1);
        return Integer.parseInt(year);
    }
    public static int getMonthNumber(int firstIndexOfSeparator, int lastIndexOfSeparator, String date) {

        String month = date.substring(firstIndexOfSeparator + 1, lastIndexOfSeparator);
        return Integer.parseInt(month);
    }
    public static int getDayNumber(int firstIndexOfSeparator, String date) {

        String dayNumber = date.substring(0, firstIndexOfSeparator);
        return Integer.parseInt(dayNumber);
    }

    public static int getIndexOfSeparator(String date, int position) {

        int storesIndex = -1;
        for (int loopVariable = 0;loopVariable < date.length();loopVariable++) {

            if (date.charAt(loopVariable) == '.' || date.charAt(loopVariable) == '-') {
                storesIndex = loopVariable;

                if (position == firstIndex) {
                    break;
                }
            }
        }
        return storesIndex;
    }

    @Override
    public String toString() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DueDate
                && this.date.equals(((DueDate) other).date));
    }
    @Override
    public int hashCode() {
        return date.hashCode();
    }


}
