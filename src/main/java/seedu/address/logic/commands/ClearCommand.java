package seedu.address.logic.commands;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import seedu.address.model.AddressBook;
import seedu.address.ui.ClearConfirmation;

/**
 * Clears the address book.
 */
public class ClearCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_FAILURE = "Address book has not been cleared!";


    @Override
    public CommandResult executeUndoableCommand() {
        requireNonNull(model);
        ClearConfirmation confirmDialog = new ClearConfirmation();
        if (confirmDialog.isClearCommand()) {
            model.resetData(new AddressBook());
            return new CommandResult(MESSAGE_SUCCESS);
        }
        else
            return new CommandResult(MESSAGE_FAILURE);
    }
}
