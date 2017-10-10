package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.ChangeTagColorCommand.MESSAGE_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagColor;

public class ChangeTagColorCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() throws Exception {
        final TagColor tagColor = new TagColor("red");
        final Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("family"));

        assertCommandFailure(prepareCommand(tags, tagColor), model,
                String.format(String.format(MESSAGE_ARGUMENTS, tags, tagColor)));
    }

    @Test
    public void equals() throws Exception {
        final TagColor tagColor = new TagColor("red");
        final Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("family"));

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
    private ChangeTagColorCommand prepareCommand(Set<Tag> tags, TagColor tagColor) {
        ChangeTagColorCommand changeTagColorCommand = new ChangeTagColorCommand(tags, tagColor);
        changeTagColorCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return changeTagColorCommand;
    }
}
