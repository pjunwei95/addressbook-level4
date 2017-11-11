package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
//@@author RonakLakhotia
public class ClearPopUpCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        assertCommandSuccessClear(model, ClearPopupCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccessClear(model, ClearPopupCommand.MESSAGE_SUCCESS, model);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the result message matches {@code expectedMessage} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */

    public static void assertCommandSuccessClear( Model actualModel, String expectedMessage,
                                            Model expectedModel) {

        assertEquals(expectedMessage, "Weaver has been cleared!");;
        assertEquals(expectedModel, actualModel);

    }
}
