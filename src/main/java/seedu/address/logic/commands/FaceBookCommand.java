package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 *  Shows a person's address on Google Maps in browser
 */
public class FaceBookCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "facebook";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Shows the profile of the user whose"
            + " username is entered\n"
            + "Example: " + COMMAND_WORD + " 1 " + " ronak.lakhotia ";

    public static final String MESSAGE_FACEBOOK_SHOWN_SUCCESS = "Profile of Person: %1$s";

    public final Index index;
    public final String username;

    public FaceBookCommand (Index index, String username) {
        this.index = index;
        this.username = username;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {

        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyPerson personToShow = lastShownList.get(index.getZeroBased());

        try {
            model.faceBook(personToShow, username);
        } catch (PersonNotFoundException pnfe) {
            assert false : "The target person cannot be missing";
        }

        return new CommandResult(String.format(MESSAGE_FACEBOOK_SHOWN_SUCCESS, personToShow));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FaceBookCommand // instanceof handles nulls
                && this.index.equals(((FaceBookCommand) other).index)); // state check
    }
}
