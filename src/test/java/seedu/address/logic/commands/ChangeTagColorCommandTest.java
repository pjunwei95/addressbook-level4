package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.ChangeTagColorCommand.MESSAGE_NOT_IMPLEMENTED_YET;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ChangeTagColorCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() throws Exception {
        assertCommandFailure(prepareCommand(), model, MESSAGE_NOT_IMPLEMENTED_YET);
    }

    /**
     * Returns an {@code RemarkCommand}.
     */
    private ChangeTagColorCommand prepareCommand() {
        ChangeTagColorCommand changeTagColorCommand = new ChangeTagColorCommand();
        changeTagColorCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return changeTagColorCommand;
    }
}