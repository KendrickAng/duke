package duke.gui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Gui {
    private static final String PREFIX = "    ";
    private static final String DIVIDER = "-------------------------------------------------------";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private static final String ADD_MESSAGE = "Got it. I've added this task:";
    private static final String SEARCH_MESSAGE = "Here are the matching tasks in your list:";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private VBox dialogContainer;
    private TextField userInput;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    protected void setContainer(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
        //System.out.println("GUI: DialogContainer " + this.dialogContainer);
    }

    protected void setInput(TextField userInput) {
        this.userInput = userInput;
        //System.out.println("INPUT: TextField" + this.userInput);
    }

    public String readCommand() {
        return userInput.getText();
    }

    public void showWelcomeMessage() {
        showToUser(DIVIDER, WELCOME_MESSAGE, DIVIDER);
        //System.out.println("GUI: DialogContainer " + this.dialogContainer);
    }

    /**
     * Prints out the message indicating a task has been added.
     *
     * @param task the task added.
     * @param taskCount the number of tasks currently in the task list.
     */
    public void showAddMessage(Task task, long taskCount) {
        showToUser(ADD_MESSAGE,
                task.toString(),
                (taskCount == 1
                        ? "Now you have 1 task in the list."
                        : String.format("Now you have %d tasks in the list.", taskCount)));
    }

    /**
     * Prints out the message indicating a task has been marked done.
     *
     * @param task the task marked done.
     */
    public void showDoneMessage(Task task) {
        showToUser(DONE_MESSAGE,
                task.toString());
    }

    /**
     * Prints out the message indicating a task has been deleted.
     *
     * @param task the deleted task.
     * @param taskCount the number of tasks remaining in the task list.
     */
    public void showDeleteMessage(Task task, long taskCount) {
        showToUser(DELETE_MESSAGE,
                task.toString(),
                (taskCount == 1
                        ? "Now you have 1 task in the list."
                        : String.format("Now you have %d tasks in the list.", taskCount)));
    }

    /**
     * Prints the exit message.
     */
    public void showExitMessage() {
        showToUser(EXIT_MESSAGE);
    }

    /**
     * Prints out the list of results from a keyword search.
     *
     * @param searchList the task list of results.
     */
    public void showSearchList(TaskList searchList) {
        showToUser(SEARCH_MESSAGE);
        showTaskList(searchList);
    }

    /**
     * Prints out a task list.
     *
     * @param taskList the task list to print.
     */
    public void showTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task != null) { // account for 0-indexing
                showToUser(String.format("%d. %s", i, task));
            }
        }
    }

    /**
     * Prints out a specified error message.
     *
     * @param e the error to print.
     */
    public void showError(DukeException e) {
        showToUser(e.getMessage());
    }

    public void showToUser(String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String line: messages) {
            sb.append(PREFIX).append(line).append('\n');
        }
        System.out.println(this.dialogContainer);
        this.dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(sb.toString(), dukeImage)
        );
    }

    public VBox getDialogContainer() {
        return this.dialogContainer;
    }
}
