package systemtests;

import static seedu.address.logic.commands.CommandTestUtil.EMAIL_SUBJECT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_TAG;
import static seedu.address.logic.commands.EmailCommand.MESSAGE_EMAIL_SUCCESS;

import java.io.IOException;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EmailCommand;
import seedu.address.model.Model;


//@@author RonakLakhotia
public class EmailCommandSystemTest extends AddressBookSystemTest {

    private static final String MESSAGE_INVALID_PHOTO_COMMAND_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EmailCommand.MESSAGE_USAGE);

    @Test
    public void email() throws IOException {
        /* ----------------- Performing photo operation while an unfiltered list is being shown -------------------- */

        /* Case: Add photo to the first person in the list, command with leading spaces and
          trailing spaces -> deleted */


        Model expectedModel = getModel();
        String command = "     " + EmailCommand.COMMAND_WORD + "      " + EMAIL_TAG + " " + EMAIL_SUBJECT;


        String expectedResultMessage = String.format(MESSAGE_EMAIL_SUCCESS);
        assertCommandSuccess(command, expectedModel, expectedResultMessage);


        /* --------------------------------- Performing invalid photo operation ------------------------------------ */



    }


    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays {@code command}.<br>
     * 2. Asserts that result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the model related components equal to the current model.<br>
     * 4. Asserts that the browser url, selected card and status bar remain unchanged.<br>
     * 5. Asserts that the command box has the error style.<br>
     * Verifications 1 to 3 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }

    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        assertCommandSuccess(command, expectedModel, expectedResultMessage, null);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Model, String)} except that the browser url
     * and selected card are expected to update accordingly depending on the card at {@code expectedSelectedCardIndex}.
     * @see PhotoCommandSystemTest#assertCommandSuccess(String, Model, String)
     * @see AddressBookSystemTest#assertSelectedCardChanged(Index)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage,
                                      Index expectedSelectedCardIndex) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);

        if (expectedSelectedCardIndex != null) {
            assertSelectedCardChanged(expectedSelectedCardIndex);
        } else {
            assertSelectedCardUnchanged();
        }


        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchangedExceptSyncStatus();
    }


}
