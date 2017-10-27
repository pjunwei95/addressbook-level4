package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_THEME_NAME;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ChangeThemeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void isValidThemeName() throws Exception {
        // invalid theme names
        assertFalse(ChangeThemeCommand.isValidThemeName("")); // empty string
        assertFalse(ChangeThemeCommand.isValidThemeName(null)); // null value
        assertFalse(ChangeThemeCommand.isValidThemeName("a")); // random words

        // valid theme names
        assertTrue(ChangeThemeCommand.isValidThemeName("dark"));
        assertTrue(ChangeThemeCommand.isValidThemeName("bright"));
    }

    @Test
    public void executeChangeThemeCommandInvalidThemeNameFailure() throws Exception {
        ChangeThemeCommand changeThemeCommand = prepareCommand(INVALID_THEME_NAME);

        String expectedMessage = String.format(ChangeThemeCommand.MESSAGE_INVALID_THEME_NAME, INVALID_THEME_NAME);

        assertCommandFailure(changeThemeCommand, model, expectedMessage);
    }

    /**
     * Returns an {@code ChangeThemeCommand} with parameters {@code theme}
     */
    private ChangeThemeCommand prepareCommand(String theme) {
        ChangeThemeCommand changeThemeCommand = new ChangeThemeCommand(theme);
        changeThemeCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return changeThemeCommand;
    }

}
