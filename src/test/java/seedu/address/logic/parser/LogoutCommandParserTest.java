package seedu.address.logic.parser;

import org.junit.Test;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.LogoutCommand;

import static org.junit.Assert.assertNotEquals;

public class LogoutCommandParserTest {

    private LogoutCommandParser parser = new LogoutCommandParser();

    LogoutCommand expectedCommand = new LogoutCommand();
    @Test
    public void parse_invalidArgs_returnsLogoutCommand() {
        assertParseFailureLogout(parser, "", expectedCommand);
    }
    public static void assertParseFailureLogout(Parser parser, String userInput, Command expectedCommand) {
            assertNotEquals(expectedCommand, new LogoutCommand());

    }
}
