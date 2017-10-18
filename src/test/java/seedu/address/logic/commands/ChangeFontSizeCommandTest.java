package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_FONT_SIZE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DECREASE_FONT_SIZE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INCREASE_FONT_SIZE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.model.font.FontSize.FONT_SIZE_L_LABEL;
import static seedu.address.model.font.FontSize.FONT_SIZE_M_LABEL;
import static seedu.address.model.font.FontSize.FONT_SIZE_S_LABEL;

import org.junit.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.font.FontSize;

import java.awt.*;

import static org.junit.Assert.*;
import static seedu.address.model.font.FontSize.FONT_SIZE_XL_LABEL;
import static seedu.address.model.font.FontSize.FONT_SIZE_XS_LABEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class ChangeFontSizeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void executeChangeFontSizeSuccess() throws Exception {
        ChangeFontSizeCommand changeFontSizeCommand = prepareCommand(FONT_SIZE_L_LABEL);
        CommandResult commandResult = changeFontSizeCommand.execute();

        String expectedMessage = ChangeFontSizeCommand.MESSAGE_SUCCESS + FONT_SIZE_L_LABEL + ".";

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(FontSize.getCurrentFontSizeLabel(), FONT_SIZE_L_LABEL);
    }

    @Test
    public void executeIncreaseFontSizeSuccess() throws Exception {
        ChangeFontSizeCommand changeFontSizeCommand = prepareCommand(VALID_INCREASE_FONT_SIZE);
        CommandResult commandResult = changeFontSizeCommand.execute();

        String expectedMessage = ChangeFontSizeCommand.MESSAGE_SUCCESS + FONT_SIZE_L_LABEL + ".";

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(FontSize.getCurrentFontSizeLabel(), FONT_SIZE_L_LABEL);
    }

    @Test
    public void executeDecreaseFontSizeSuccess() throws Exception {
        ChangeFontSizeCommand changeFontSizeCommand = prepareCommand(VALID_DECREASE_FONT_SIZE);
        CommandResult commandResult = changeFontSizeCommand.execute();

        String expectedMessage = ChangeFontSizeCommand.MESSAGE_SUCCESS + FONT_SIZE_S_LABEL + ".";

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(FontSize.getCurrentFontSizeLabel(), FONT_SIZE_S_LABEL);
    }

    @Test
    public void executeInvalidFontSizeFailure() throws Exception {
        ChangeFontSizeCommand changeFontSizeCommand = prepareCommand(INVALID_FONT_SIZE);

        String expectedMessage = FontSize.MESSAGE_FONT_SIZE_CONSTRAINTS;

        assertCommandFailure(changeFontSizeCommand, model, expectedMessage);
    }

    @Test
    public void equals() throws Exception {
        final ChangeFontSizeCommand standardCommand = new ChangeFontSizeCommand(FONT_SIZE_L_LABEL);

        // same values -> returns true
        ChangeFontSizeCommand commandWithSameValues = new ChangeFontSizeCommand(FONT_SIZE_L_LABEL);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different value -> returns false
        assertFalse(standardCommand.equals(new ChangeFontSizeCommand(FONT_SIZE_M_LABEL)));

        // different value -> returns false
        assertFalse(standardCommand.equals(new ChangeFontSizeCommand(FONT_SIZE_S_LABEL)));
    }
    /**
     * Returns an {@code ChangeFontSizeCommand} with parameters {@code tags} and {@code color}
     */
    private ChangeFontSizeCommand prepareCommand(String fontSize) throws IllegalValueException {
        ChangeFontSizeCommand changeFontSizeCommand = new ChangeFontSizeCommand(fontSize);
        changeFontSizeCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return changeFontSizeCommand;
    }
}
