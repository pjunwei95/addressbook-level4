package seedu.address.testutil;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.person.ReadOnlyPerson;

import static seedu.address.logic.parser.CliSyntax.*;


/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(ReadOnlyPerson person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns an remark command string for adding the {@code person}.
     */
    public static String getRemarkCommand(ReadOnlyPerson person) {
        return RemarkCommand.COMMAND_WORD + " " + "1 r/CS2101/SEC/1";
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(ReadOnlyPerson person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_DOB + person.getDateOfBirth().date + " ");
        sb.append(PREFIX_IMAGE + person.getImage().filePath + " ");
        sb.append(PREFIX_USERNAME + person.getUsername().username + " ");
        sb.append(PREFIX_REMARK + person.getRemark().getModuleLists() + " ");

        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
