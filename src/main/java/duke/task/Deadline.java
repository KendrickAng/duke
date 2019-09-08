package duke.task;

import duke.exception.DukeUnknownInputException;
import duke.time.DateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private String deadline;

    Deadline(String description, String deadline) throws DukeUnknownInputException {
        this(description, deadline, false);
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTime.of(deadline) + ')';
    }
}
