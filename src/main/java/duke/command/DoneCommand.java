package duke.command;

import duke.Ui;
import duke.gui.Gui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents an instruction to mark a task as completed.
 */
public class DoneCommand extends Command {
    private int idx;

    public DoneCommand(int idx, boolean isExit) {
        super(isExit);
        this.idx = idx;
    }

    /**
     * Marks a task in the TaskList as done, informs the user and updates the hard disk.
     *
     * @param taskList the TaskList instance Duke is referencing.
     * @param gui the Gui instance handling user-facing interaction.
     * @param storage the Storage instance dealing with hard disk reading/writing.
     */
    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        // mark the task corresponding to index as done
        Task task = taskList.markAsDone(idx);

        // inform the user task has been marked done
        gui.showDoneMessage(task);

        // update hard disk
        storage.writeToDisk(taskList);
    }
}
