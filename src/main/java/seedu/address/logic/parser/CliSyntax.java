package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_COLOR = new Prefix("c/");
    //@@author RonakLakhotia
    public static final Prefix PREFIX_DOB = new Prefix("b/");
    //@@author
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    //@@author RonakLakhotia
    public static final Prefix PREFIX_IMAGE = new Prefix("f/");
    //@@author
    public static final Prefix PREFIX_FONT_SIZE = new Prefix("");
    //@@author RonakLakhotia
    public static final Prefix PREFIX_USERNAME = new Prefix("u/");
    public static final Prefix PREFIX_REMINDER_DUE_DATE = new Prefix("d/");
    public static final Prefix PREFIX_REMINDER_PRIORITY = new Prefix("p/");
    public static final Prefix PREFIX_REMINDER_DETAILS = new Prefix("g/");
}
