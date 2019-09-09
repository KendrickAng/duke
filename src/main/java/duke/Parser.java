package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.DukeIndexOutOfBoundsException;
import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeUnknownInputException;
import duke.task.TaskType;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;

import static duke.task.TaskType.*;

/**
 * Deals with making sense of commands.
 */
public class Parser {
    public static final Locale LOCALE = Locale.ENGLISH;
    public static final String DEADLINE_PARSE_PATTERN = "ddMMyy HHmm";
    public static final String DEADLINE_FORMAT_PATTERN = "dd MMMM yyyy, hh:mma, O";

    /**
     * Parses the full command and returns the corresponding Command instance.
     *
     * @param command the full command to parse.
     * @return the command corresponding to the input.
     * @throws DukeException an exception generated when parsing the input.
     */
    public static Command parse(String command) throws DukeException {
        // remove trailing/leading whitespace and split by whitespace(s)
        command = command.strip();
        String[] commands = command.split("[ ]+");
        String[] args = Arrays.copyOfRange(commands, 1, commands.length);

        assert args.length == commands.length - 1 : "Incorrect array copy";

        switch (commands[0]) {
        case "todo":
            return parseTodo(args);
        case "event":
            return parseEvent(args);
        case "deadline":
            return parseDeadline(args);
        case "find":
            return parseFind(args);
        case "done":
            return parseDone(args);
        case "delete":
            return parseDelete(args);
        case "list":
            return parseList(args);
        case "remindme":
            return parseRemind(args);
        case "bye":
            return parseBye(args);
        default:
            throw new DukeMissingDescriptionException("OOPS! I'm sorry, but I don't know what that means...");
        }
    }

    /*
    Business logic for each command. Refactor concept: Extract Method
     */
    private static Command parseTodo(String[] args) throws DukeMissingDescriptionException {
        if (args.length == 0) {
            throw new DukeMissingDescriptionException("OOPS! The description of a todo cannot be empty.");
        }
        return new AddCommand(TODO, args, false);
    }

    private static Command parseEvent(String[] args) throws DukeUnknownInputException {
        String[] eventArgs = String.join(" ", args).split(" /at ");
        if (eventArgs.length != 2) {
            throw new DukeUnknownInputException("Incorrect argument count for event!");
        }

        return new AddCommand(EVENT, args, false);
    }

    private static Command parseDeadline(String[] args) throws DukeUnknownInputException {
        String[] deadlineArgs = String.join(" ", args).split(" /by ");
        if (deadlineArgs.length != 2) {
            throw new DukeUnknownInputException("Incorrect argument count for deadline!");
        }
        // enforce deadline format ddMMyy HHmm
        parseDeadline(deadlineArgs[1]);

        return new AddCommand(DEADLINE, args, false);
    }

    private static Command parseFind(String[] args) throws DukeMissingDescriptionException {
        if (args.length == 0) {
            throw new DukeMissingDescriptionException("OOPS! The keyword of a search cannot be empty.");
        }

        String keyword = args[0].strip();
        return new FindCommand(keyword, false);
    }

    private static Command parseDone(String[] args) throws DukeIndexOutOfBoundsException {
        int doneIdx = Integer.valueOf(args[0]);
        if (doneIdx < 0) {
            throw new DukeIndexOutOfBoundsException("Attempting to mark task not in list!");
        }

        return new DoneCommand(doneIdx, false);
    }

    private static Command parseDelete(String[] args) throws DukeIndexOutOfBoundsException {
        int deleteIdx = Integer.valueOf(args[0]);
        if (deleteIdx < 0) {
            throw new DukeIndexOutOfBoundsException("Attempting to delete task not in list!");
        }

        return new DeleteCommand(deleteIdx, false);
    }

    private static Command parseList(String[] args) {
        return new ListCommand(false);
    }

    private static Command parseRemind(String[] args) {
        if (args.length != 1) {
            throw new DukeUnknownInputException("Incorrect argument count for remind, should be 1");
        }

        TaskType type;
        if (args[0].equals("deadline")) {
            type = DEADLINE;
        } else if (args[0].equals("event")) {
            type = EVENT;
        } else if (args[0].equals("all")) {
            type = ALL;
        } else {
            throw new DukeUnknownInputException(
                    String.format("Unknown argument, should be %s or %s", DEADLINE, EVENT));
        }

        return new RemindCommand(type, false);
    }

    private static Command parseBye(String[] args) {
        return new ExitCommand(true);
    }

    /**
     * Business logic for date and time parsing. Code adapted from duke/WeomuCat
     */

    /**
     * Creates a DateTime object with format specified by the format pattern.
     *
     * @param in the input string, which must follow the parse pattern.
     * @param type the task type in which are parsing for, which differs between Event types.
     * @return the ZonedDateTime object.
     * @throws DukeUnknownInputException if the input string is of invalid format, or an invalid task type is passed.
     */
    public static ZonedDateTime parseDateTime(String in, TaskType type) throws DukeUnknownInputException {
        // switch allows us to parse dateTimes differently by task type. see parseDeadline for exampe.
        switch (type) {
        case DEADLINE:
            return parseDeadline(in);
        default:
            throw new DukeUnknownInputException("Invalid task specified, please pass Deadlines");
        }
    }

    private static ZonedDateTime parseDeadline(String by) throws DukeUnknownInputException {
        try {
            DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern(DEADLINE_PARSE_PATTERN, LOCALE)
                    .withZone(ZoneId.systemDefault());
            return ZonedDateTime.parse(by, parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeUnknownInputException(
                    String.format("Invalid date format. Please follow the format %s", DEADLINE_PARSE_PATTERN));
        }
    }
}
