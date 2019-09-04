package duke.command;

import duke.Ui;
import duke.gui.Gui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an instruction to list all tasks.
 */
public class ListCommand extends Command {
    public ListCommand(boolean isExit) {
        super(isExit);
    }

    /**
     * Lists all tasks in Duke's TaskList.
     *
     * @param taskList the TaskList instance Duke is referencing.
     * @param gui the Gui instance handling user-facing interaction.
     * @param storage the Storage instance dealing with hard disk reading/writing.
     */
    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        gui.showTaskList(taskList);
    }
}
