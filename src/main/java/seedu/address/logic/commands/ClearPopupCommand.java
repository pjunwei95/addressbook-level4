package seedu.address.logic.commands;
//@@author pjunwei95
import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.ui.ClearConfirmation;

/**
 * Clears the address book.
 */
public class ClearPopupCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Weaver has been cleared!";
    public static final String MESSAGE_FAILURE = "Weaver has not been cleared!";


    @Override
    public CommandResult executeUndoableCommand() {
        ClearConfirmation clearConfirmation = new ClearConfirmation();
        if (clearConfirmation.isClearCommand()) {
            requireNonNull(model);
            model.resetData(new AddressBook());
            model.clearBrowserPanel();
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
