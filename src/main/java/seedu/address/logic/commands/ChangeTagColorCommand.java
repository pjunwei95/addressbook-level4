package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagColor;

/**
 * Change color of tags
 */
public class ChangeTagColorCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "color";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the color of a tag or list of tags. "
            + "Existing color will be overwritten by the input.\n"
            + "Parameters: " + COMMAND_WORD + " "
            + PREFIX_TAG + "[TAG] "
            + PREFIX_COLOR + "[COLOR]\n"
            + "Example: " + COMMAND_WORD + " t/friend "
            + "c/red";

    public static final String MESSAGE_ARGUMENTS = "Tag: %1$s, Color: %2$s";

    private final Set<Tag> tagList;
    private final TagColor color;

    /**
     * @param tagList to edit the color
     * @param color of the tagList
     */
    public ChangeTagColorCommand(Set<Tag> tagList, TagColor color) {
        requireNonNull(tagList);
        requireNonNull(color);

        this.tagList = tagList;
        this.color = color;
    }


    @Override
    protected CommandResult executeUndoableCommand() throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, tagList, color));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ChangeTagColorCommand)) {
            return false;
        }

        // state check
        ChangeTagColorCommand e = (ChangeTagColorCommand) other;
        return tagList.equals(e.tagList)
                && color.equals(e.color);
    }
}
