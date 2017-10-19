package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGE;

import java.io.IOException;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.storage.ImageStorage;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.FileImage;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Adds a display picture to an existing person in address book
 */
public class PhotoCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "photo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds/Updates the profile picture of a person identified "
            + "by the index number used in the last person listing. "
            + "Existing Display picture will be updated by the image referenced in the input path. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_IMAGE + "[PATH]\n"
            + "Example: " + COMMAND_WORD + " 2 "
            + "C:\\Users\\Admin\\Desktop\\pic.jpg";

    public static final String MESSAGE_ADD_DISPLAYPICTURE_SUCCESS = "Added Display Picture to Person: %1$s";

    public static final String MESSAGE_DELETE_DISPLAYPICTURE_SUCCESS = "Removed Display Picture from Person: %1$s";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    public static final String MESSAGE_FILE_PATH_NOT_FOUND =
            "This specified path cannot be read. Please check it's validity and try again";


    private Index index;
    private FileImage displayPicture;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param displayPicture of the person
     */
    public PhotoCommand(Index index, FileImage displayPicture) {

        this.index = index;
        this.displayPicture = displayPicture;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();
        ReadOnlyPerson personToEdit = lastShownList.get(index.getZeroBased());

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (displayPicture.getFilePath().equalsIgnoreCase("")) {
            displayPicture.setFilePath("");

            Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                    personToEdit.getAddress(), personToEdit.getDateOfBirth(), personToEdit.getRemark(),
                    displayPicture, personToEdit.getTags());

            try {
                model.updatePerson(personToEdit, editedPerson);
            } catch (DuplicatePersonException dpe) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            } catch (PersonNotFoundException pnfe) {
                throw new AssertionError("The target person cannot be missing");
            }
            model.updateFilteredListToShow();

            return new CommandResult(generateSuccessMessage(editedPerson));
        }

        try {
            ImageStorage readAndStoreImage = new ImageStorage();
            displayPicture.setFilePath(readAndStoreImage.execute(displayPicture.getFilePath(),
                    personToEdit.getEmail().hashCode()));
        } catch (IOException ioe) {
            displayPicture.setFilePath("");
            return new CommandResult(generateFailureMessage());
        }

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getDateOfBirth(), personToEdit.getRemark(),
                displayPicture, personToEdit.getTags());

        try {
            model.updatePerson(personToEdit, editedPerson);
        } catch (DuplicatePersonException dpe) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        } catch (PersonNotFoundException pnfe) {
            throw new AssertionError("The target person cannot be missing");
        }
        model.updateFilteredListToShow();

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates failure message
     * @return String
     */
    private String generateFailureMessage() {
        return MESSAGE_FILE_PATH_NOT_FOUND;
    }

    /**
     * Generates success message
     * @param personToEdit is checked
     * @return String
     */
    private String generateSuccessMessage(ReadOnlyPerson personToEdit) {
        if (!displayPicture.getFilePath().isEmpty()) {
            return String.format(MESSAGE_ADD_DISPLAYPICTURE_SUCCESS, personToEdit);
        } else {
            return String.format(MESSAGE_DELETE_DISPLAYPICTURE_SUCCESS, personToEdit);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PhotoCommand)) {
            return false;
        }

        // state check
        PhotoCommand e = (PhotoCommand) other;
        return index.equals(e.index)
                && displayPicture.equals(e.displayPicture);
    }
}