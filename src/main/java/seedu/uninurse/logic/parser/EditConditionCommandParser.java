package seedu.uninurse.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.EditConditionCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.condition.Condition;

/**
 * Parses input arguments and creates a new EditConditionCommand object.
 */
public class EditConditionCommandParser implements Parser<EditConditionCommand> {
    public static final String MESSAGE_MISSING_CONDITION = "Condition to edit must be provided.";

    /**
     * Parses the given {@code String} of arguments in the context of the EditConditionCommand
     * and returns an EditConditionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditConditionCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CONDITION);

        try {
            List<Index> indices = ParserUtil.parseTwoIndex(argMultimap.getPreamble());
            Condition condition = ParserUtil.parseCondition(argMultimap.getValue(PREFIX_CONDITION).orElseThrow());
            return new EditConditionCommand(indices.get(0), indices.get(1), condition);
            // TODO: display specific error message for invalid condition
        } catch (ParseException pe) {
            // handles invalid indices and conditions
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditConditionCommand.MESSAGE_USAGE), pe);
        } catch (NoSuchElementException nse) {
            // Handles missing prefix
            throw new ParseException(MESSAGE_MISSING_CONDITION);
        }
    }
}
