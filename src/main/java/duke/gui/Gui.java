package duke.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    protected void setContainer(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    public void showWelcomeMessage() {
        showToUser(DIVIDER, WELCOME_MESSAGE, DIVIDER);
    }

    private void showToUser(String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String line: messages) {
            sb.append(PREFIX).append(line).append('\n');
        }
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(sb.toString(), dukeImage)
        );
    }
}
