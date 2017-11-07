//@@author ChenXiaoman
package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAGLIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAGLIST_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COLOR_NAME_RED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagColor;

/**
 * Contains integration tests (interaction with the Model) for {@code ChangeTagColorCommand}.
 */
public class ChangeTagColorCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void executeChangeTagColorOfATagListSuccess() throws Exception {
        TagColor tagColor = new TagColor(VALID_TAG_COLOR_NAME_RED);
        ChangeTagColorCommand changeTagColorCommand = prepareCommand(VALID_TAGLIST_1, VALID_TAG_COLOR_NAME_RED);

        String expectedMessage = String.format(ChangeTagColorCommand.MESSAGE_CHANGE_TAG_COLOR_SUCCESS,
                VALID_TAGLIST_1, VALID_TAG_COLOR_NAME_RED);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateTagColorPair(VALID_TAGLIST_1, tagColor);

        assertCommandSuccess(changeTagColorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeChangeTagColorOfAnInvalidTagListFailure() throws Exception {
        ChangeTagColorCommand changeTagColorCommand = prepareCommand(INVALID_TAGLIST, VALID_TAG_COLOR_NAME_RED);

        String expectedMessage = String.format(ChangeTagColorCommand.MESSAGE_NOT_EXISTING_TAGS, INVALID_TAGLIST);

        assertCommandFailure(changeTagColorCommand, model, expectedMessage);
    }

    @Test
    public void equals() throws Exception {
        final TagColor tagColor = new TagColor("red");
        final Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("colleagues"));

        final TagColor tagColor2 = new TagColor("blue");
        final Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("friend"));

        final ChangeTagColorCommand standardCommand = new ChangeTagColorCommand(tags, tagColor);

        // same values -> returns true
        ChangeTagColorCommand commandWithSameValues = new ChangeTagColorCommand(tags, tagColor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ChangeTagColorCommand(tags2, tagColor)));

        // different remarks -> returns false
        assertFalse(standardCommand.equals(new ChangeTagColorCommand(tags, tagColor2)));
    }

    /**
     * Returns an {@code ChangeTagColorCommand} with parameters {@code tags} and {@code color}
     */
    private ChangeTagColorCommand prepareCommand(Set<Tag> tags, String tagColor) {
        ChangeTagColorCommand changeTagColorCommand = null;
        try {
            changeTagColorCommand = new ChangeTagColorCommand(tags, new TagColor(tagColor));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
        changeTagColorCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return changeTagColorCommand;
    }
}
