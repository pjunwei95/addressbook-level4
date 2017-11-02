package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.tag.Tag;

/**
 * Sends an Email to all contacts with the specified tag.
 */
public class EmailCommand extends Command {

    public static final String COMMAND_WORD = "email";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the default browser on the desktop with the Gmail "
            + "compose box open and specific details pre-filled.\n"
            + "Parameters: " + PREFIX_TAG + "Only one TAG must be entered (must match tag) \n"
            + PREFIX_SUBJECT + "Subject of the email."
            + "Example: " + COMMAND_WORD + " t/friends s/birthday.";

    public static final String MESSAGE_EMAIL_SUCCESS = "Email has been sent!";
    public static final String MESSAGE_NOT_EXISTING_TAGS = "The tag provided is invalid. Please check again.";

    private final String tag;
    private final String subject;

    /**
     * @param tag     of the persons to whom the email has to be sent
     * @param subject the subject line of th email
     */

    public EmailCommand(String tag, String subject) {
        requireNonNull(tag);
        requireNonNull(subject);

        this.tag = tag;
        this.subject = subject;
    }

    @Override
    public CommandResult execute() throws CommandException {

        boolean isExistingTagName = checkIfExistingTagName(tag);

        try {
            if (!isExistingTagName) {

                throw new CommandException(String.format(MESSAGE_NOT_EXISTING_TAGS));
            }
            else {
                sendMailToContacts(tag, subject);
                return new CommandResult(MESSAGE_EMAIL_SUCCESS);
            }
        }
        catch (IllegalValueException ive) {

            throw new AssertionError("Illegal Value error");
        }
    }

    /**
     * Check whether a given tag exists in address book.
     *
     * @param tagName tag that is to be checked
     */
    private boolean checkIfExistingTagName(String tagName) {
        for (Tag tag : model.getAddressBook().getTagList()) {
            if (tag.tagName.equalsIgnoreCase(tagName)) {
                return true;
            }
        }
        return false;
    }

    private void sendMailToContacts(String tag, String subject) throws IllegalValueException{

        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();
        String appendEmailAddress = "";

        appendEmailAddress = getAppendedEmailIdOfContacts(tag, lastShownList, appendEmailAddress);
        openUpDesktopBrowser(appendEmailAddress, subject);

    }

    private String getAppendedEmailIdOfContacts(String tag, List<ReadOnlyPerson> lastShownList,
                                                String appendEmailAddress) throws IllegalValueException {

        ReadOnlyPerson getPerson;
        int loopVariable = 0;

        while (loopVariable < lastShownList.size()) {
            getPerson = lastShownList.get(loopVariable);

            if (getPerson.getTags().contains(new Tag(tag))) {
                appendEmailAddress = appendEmailAddress + getPerson.getEmail().toString() + "+";
            }
            loopVariable++;

        }
        return appendEmailAddress;
    }

    private void openUpDesktopBrowser(String appendEmailAddress, String subject) {

        appendEmailAddress = appendEmailAddress.substring(0,appendEmailAddress.length()-1);

        String GMAIL_URL = "https://mail.google.com/mail/?view=cm&fs=1&to=" + appendEmailAddress + "&su=" + subject;


        try {

            if(Desktop.isDesktopSupported())
            {
                Desktop.getDesktop().browse(new URI(GMAIL_URL));
            }
        } catch (URISyntaxException U ) {
            throw new AssertionError("URISyntax error");

        } catch (IOException IE) {
            throw new AssertionError("IOE error");

        }
    }
}
