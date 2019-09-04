package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
//    private static final String PREFIX = "    ";
//    private static final String DIVIDER = "____________________________________________________________";
//    private static final String WELCOME_MESSAGE = "Hello! I'm Duke. What can I do for you?";
//    private static final String ADD_MESSAGE = "Got it. I've added this task:";
//    private static final String SEARCH_MESSAGE = "Here are the matching tasks in your list:";
//    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
//    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
//    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
//
//    private Scanner in;
//    private PrintStream out;
//
//    public Ui() {
//        this(System.in, System.out);
//    }
//
//    private Ui(InputStream in, PrintStream out) {
//        this.in = new Scanner(in);
//        this.out = out;
//    }
//
//    /**
//     * Prints out a divider.
//     */
//    public void showLine() {
//        showToUser(DIVIDER);
//    }
//
//    /**
//     * Prints out a specified error message.
//     *
//     * @param e the error to print.
//     */
//    public void showError(DukeException e) {
//        showToUser(e.getMessage());
//    }
//
//    /**
//     * Prints out a welcome message.
//     */
//    public void showWelcomeMessage() {
//        showToUser(DIVIDER, WELCOME_MESSAGE, DIVIDER);
//    }
//
//    /**
//     * Reads a full command from the next line of input.
//     *
//     * @return the full command read.
//     */
//    public String readCommand() {
//        return in.nextLine();
//    }
//
//    /**
//     * Prints out the message indicating a task has been added.
//     *
//     * @param task the task added.
//     * @param taskCount the number of tasks currently in the task list.
//     */
//    public void showAddMessage(Task task, long taskCount) {
//        showToUser(ADD_MESSAGE,
//                task.toString(),
//                (taskCount == 1
//                    ? "Now you have 1 task in the list."
//                    : String.format("Now you have %d tasks in the list.", taskCount)));
//    }
//
//    /**
//     * Prints out the message indicating a task has been marked done.
//     *
//     * @param task the task marked done.
//     */
//    public void showDoneMessage(Task task) {
//        showToUser(DONE_MESSAGE,
//                task.toString());
//    }
//
//    /**
//     * Prints out the message indicating a task has been deleted.
//     *
//     * @param task the deleted task.
//     * @param taskCount the number of tasks remaining in the task list.
//     */
//    public void showDeleteMessage(Task task, long taskCount) {
//        showToUser(DELETE_MESSAGE,
//                task.toString(),
//                (taskCount == 1
//                    ? "Now you have 1 task in the list."
//                    : String.format("Now you have %d tasks in the list.", taskCount)));
//    }
//
//    /**
//     * Prints out the list of results from a keyword search.
//     *
//     * @param searchList the task list of results.
//     */
//    public void showSearchList(TaskList searchList) {
//        showToUser(SEARCH_MESSAGE);
//        showTaskList(searchList);
//    }
//
//    /**
//     * Prints out a task list.
//     *
//     * @param taskList the task list to print.
//     */
//    public void showTaskList(TaskList taskList) {
//        ArrayList<Task> tasks = taskList.getTaskList();
//        for (int i = 0; i < tasks.size(); i++) {
//            Task task = tasks.get(i);
//            if (task != null) { // account for 0-indexing
//                showToUser(String.format("%d. %s", i, task));
//            }
//        }
//    }
//
//    /**
//     * Prints the exit message.
//     */
//    public void showExitMessage() {
//        showToUser(EXIT_MESSAGE);
//    }
//
//    /**
//     * Prints out a sequence of messages with prefix added to each line.
//     *
//     * @param messages the sequence of messages to print.
//     */
//    private void showToUser(String... messages) {
//        for (String line: messages) {
//            out.println(PREFIX + line);
//        }
//    }
}
