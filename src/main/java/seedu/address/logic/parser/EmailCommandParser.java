package seedu.address.logic.parser;
//@@author RonakLakhotia
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_IMAGE;

import java.io.File;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.EmailCommand;
import seedu.address.logic.commands.PhotoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PhotoCommand object
 */
public class EmailCommandParser implements Parser<EmailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PhotoCommand
     * and returns an PhotoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EmailCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();


        String regex = "[\\s]+";
        String[] keywords = trimmedArgs.split(regex, 2);

        if (keywords.length == 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmailCommand.MESSAGE_USAGE)
            );
        }
        else if (keywords.length != 1) {

                return new EmailCommand(keywords[0], (keywords[1]));


        }
        else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_IMAGE, PhotoCommand.MESSAGE_USAGE));
        }

    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
