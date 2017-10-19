package seedu.address.logic.commands;

import java.io.IOException;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.Messages;
import seedu.address.commons.events.storage.ImageStorage;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.FileImage;
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

    public static final String MESSAGE_PHOTO_DELETE_SUCCESS = "Deleted Photo of Person: %1$s";

    public static final String MESSAGE_FILE_PATH_NOT_FOUND = "Please enter valid File path";

    private final Index targetIndex;
    private final FileImage FilePath;

    public PhotoCommand(Index targetIndex, FileImage FilePath) {
        this.targetIndex = targetIndex;
        this.FilePath = FilePath;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {

        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();
      /*
        if(FilePath.getFilePath().equalsIgnoreCase("")) {
            FilePath.setFilePath("");

            Person personToEdit = new Person(personToAddPhoto.getName(), personToAddPhoto.getPhone(),
                    personToAddPhoto.getEmail(), personToAddPhoto.getAddress(), personToAddPhoto.getDateOfBirth(),
                    personToAddPhoto.getRemark(), FilePath, personToAddPhoto.getTags());

            try {
                model.updatePerson(personToAddPhoto, personToEdit);
            }
            catch (DuplicatePersonException dp) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }
            catch (PersonNotFoundException pfe) {
                throw new AssertionError("Person not present");
            }
            model.updateFilteredListToShow();
            return new CommandResult(getMessage(personToEdit));

            }
        try {


            ImageStorage imageStorage = new ImageStorage();
            FilePath.setFilePath(imageStorage.execute(FilePath.getFilePath(),
                    personToAddPhoto.getEmail().hashCode()));
            model.addPhotoPerson(personToAddPhoto, FilePath.getFilePath());
        }
        catch (IOException ioe) {
            FilePath.setFilePath("");
            return new CommandResult(generateFaliure());
        }
        catch (PersonNotFoundException PNE) {
            throw new AssertionError("Person Not present");
        }

        Person personToEdit = new Person(personToAddPhoto.getName(), personToAddPhoto.getPhone(),
                personToAddPhoto.getEmail(), personToAddPhoto.getAddress(), personToAddPhoto.getDateOfBirth(),
                personToAddPhoto.getRemark(), FilePath, personToAddPhoto.getTags());

        try {
            model.updatePerson(personToAddPhoto, personToEdit);
        }
        catch (DuplicatePersonException dp) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        catch (PersonNotFoundException pfe) {
            throw new AssertionError("Person not present");
        }
        model.updateFilteredListToShow();
        return new CommandResult(getMessage(personToEdit));

    }
    */

       ReadOnlyPerson personToAddPhoto = lastShownList.get(targetIndex.getZeroBased());
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        try {


            ImageStorage imageStorage = new ImageStorage();
            FilePath.setFilePath(imageStorage.execute(FilePath.getFilePath(),
                    personToAddPhoto.getEmail().hashCode()));
            model.addPhotoPerson(personToAddPhoto, FilePath.getFilePath());

        } catch (PersonNotFoundException pnfe) {
            assert false : "The target person cannot be missing";
        }
        catch (IOException pnfe) {
            assert false : "The target person cannot be missing";
        }


        return new CommandResult(getMessage(personToAddPhoto));
    }


    public String getMessage(ReadOnlyPerson personToEdit) {
        if(!FilePath.getFilePath().isEmpty()) {
            return String.format(MESSAGE_PHOTO_PERSON_SUCCESS, personToEdit);
        }
        else {
            return String.format(MESSAGE_PHOTO_DELETE_SUCCESS, personToEdit);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhotoCommand // instanceof handles nulls
                && this.targetIndex.equals(((PhotoCommand) other).targetIndex)); // state check
    }
}
