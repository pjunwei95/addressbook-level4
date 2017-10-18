package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Adds a photo of the person to the addressBook
 */

public class PhotoCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "photo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a photo of the person to the addressBook\n"
            + "Parameters: INDEX (must be a positive integer) FILE_PATH\n"
            + "Example: " + COMMAND_WORD + " 1" + " button.png";

    public static final String MESSAGE_PHOTO_PERSON_SUCCESS = "Added Photo to Person: %1$s";

    private final Index targetIndex;
    private final String FilePath;

    public PhotoCommand(Index targetIndex, String FilePath) {
        this.targetIndex = targetIndex;
        this.FilePath = FilePath;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {

        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyPerson personToAddPhoto = lastShownList.get(targetIndex.getZeroBased());

        try {
            model.addPhotoPerson(personToAddPhoto, FilePath);
        } catch (PersonNotFoundException pnfe) {
            assert false : "The target person cannot be missing";
        }

        return new CommandResult(String.format(MESSAGE_PHOTO_PERSON_SUCCESS, personToAddPhoto));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhotoCommand // instanceof handles nulls
                && this.targetIndex.equals(((PhotoCommand) other).targetIndex)); // state check
    }
}



