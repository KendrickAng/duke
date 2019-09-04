package duke.command;

import duke.Ui;
import duke.gui.Gui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskFactory;
import duke.task.TaskList;
import duke.task.TaskType;

/**
 * Represents an instruction to add a new Task to Duke.
 */
public class AddCommand extends Command {
    private TaskType type;
    private String[] args;

    public AddCommand(TaskType type, String[] args, boolean isExit) {
        super(isExit);
        this.type = type;
        this.args = args;
    }

    /**
     * Adds a task to the TaskList, informs the user and updates the hard disk.
     *
     * @param taskList the TaskList instance Duke is referencing.
     * @param gui the Gui instance handling user-facing interaction.
     * @param storage the Storage instance dealing with hard disk reading/writing.
     */
    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        // create the appropriate task, add to the list and write to disk
        Task task = taskList.add(TaskFactory.getTask(type, args));

        // inform the user the task has been added
        gui.showAddMessage(task, taskList.count());

        // update hard disk
        storage.writeToDisk(taskList);
    }

    public String[] getArgs() {
        return this.args;
    }
}
