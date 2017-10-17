package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FONT_SIZE;
import static seedu.address.model.font.FontSize.FONT_SIZE_L_LABEL;
import static seedu.address.model.font.FontSize.FONT_SIZE_M_LABEL;
import static seedu.address.model.font.FontSize.FONT_SIZE_S_LABEL;
import static seedu.address.model.font.FontSize.FONT_SIZE_XL_LABEL;
import static seedu.address.model.font.FontSize.FONT_SIZE_XS_LABEL;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ChangeFontSizeEvent;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.font.FontSize;

/**
 * Customise the look of the Address Book application.
 */
public class ChangeFontSizeCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "fontsize";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Customize the font size "
            + "Parameters: "
            + PREFIX_FONT_SIZE + "[FONT SIZE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FONT_SIZE + "xs\n";
    public static final String MESSAGE_SUCCESS = "Changed font size to ";

    private final FontSize fontSize;

    public ChangeFontSizeCommand(FontSize fontSize) {
        this.fontSize = fontSize;
    };

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        String newFontSize = fontSize.value;

        // Font size is valid
        if (FontSize.isValidFontSize(newFontSize)) {
            EventsCenter.getInstance().post(new ChangeFontSizeEvent(MESSAGE_SUCCESS + newFontSize + ".",
                    newFontSize));
            return new CommandResult(MESSAGE_SUCCESS + newFontSize + ".");
        } else {
            throw new CommandException(FontSize.MESSAGE_FONT_SIZE_CONSTRAINTS);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ChangeFontSizeCommand // instanceof handles nulls
                && fontSize.equals(((ChangeFontSizeCommand) other).fontSize));
    }
}
