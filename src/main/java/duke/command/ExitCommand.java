package duke.command;

import duke.Ui;
import duke.gui.Gui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an instruction to quit Duke.
 */
public class ExitCommand extends Command {
    public ExitCommand(boolean isExit) {
        super(isExit);
    }

    /**
     * Quits Duke.
     *
     * @param taskList the TaskList instance Duke is referencing.
     * @param gui the Ui instance handling user-facing interaction.
     * @param storage the Storage instance dealing with hard disk reading/writing.
     */
    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        gui.showExitMessage();
    }
}
