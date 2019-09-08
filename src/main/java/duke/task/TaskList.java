package duke.task;

import duke.exception.DukeIndexOutOfBoundsException;

import java.util.ArrayList;

/**
 * Represents a list of tasks in Duke.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        tasks.add(null); // leave index 0 unused for clarity.
    }

    /**
     * Add a task to the list.
     *
     * @param task the task to add.
     * @return the task added.
     */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Deletes a task from the list.
     *
     * @param idx the index of the task to delete (as listed).
     * @return the deleted task.
     * @throws DukeIndexOutOfBoundsException if the index of the task to delete is not shown (as listed).
     */
    public Task delete(int idx) throws DukeIndexOutOfBoundsException {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException(":'( OOPS!!! There's no such task index.");
        }
        return tasks.remove(idx);
    }

    /**
     * Gets a task from the list.
     *
     * @param idx the index of the task to get (as listed).
     * @return the task obtained.
     * @throws DukeIndexOutOfBoundsException if the index of the task to delete is not shown (as listed).
     */
    public Task get(int idx) throws DukeIndexOutOfBoundsException {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException(":'( OOPS!!! There's no such task index.");
        }
        return tasks.get(idx);
    }

    /**
     * Finds all tasks matching a keyword given.
     *
     * @param keyword the keyword to search among task descriptions for.
     * @return the task list containing all matched tasks.
     */
    public TaskList find(String keyword) {
        TaskList newList = new TaskList();
        for (Task task: this.getTaskList()) {
            if (task != null) {
                if(task.getDescription().contains(keyword)) {
                    newList.add(task);
                }
            }
        }
        return newList;
    }

    /**
     * Marks a task as completed.
     *
     * @param idx the index of the task to mark as done (as listed).
     * @return the task marked as complete.
     * @throws DukeIndexOutOfBoundsException if the index of the task to delete is not shown (as listed).
     */
    public Task markAsDone(int idx) throws DukeIndexOutOfBoundsException {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException(":'( OOPS!!! There's no such task index.");
        }
        Task task = tasks.get(idx);
        task.markAsDone();
        return task;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public long count() {
        return tasks.size() - 1; // account for 1-indexing.
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
