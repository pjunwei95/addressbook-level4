package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;

public class ChangeTagColorCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "color";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Remark command not implemented yet";

    @Override
    protected CommandResult executeUndoableCommand() throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
