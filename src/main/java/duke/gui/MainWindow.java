package duke.gui;

import duke.Duke;
// the @FXML notation marks a private or protected member, allowing FXML to access it despite the modifier.
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * A UI Controller class. For UI-related code.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Gui gui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public MainWindow() {
        this.gui = new Gui();
    }

    /**
     * Initializes customised node properties.
     */
    @FXML
    public void initialize() {
        // The constructor is called, then the @FXML fields populated, then initialize() is called.
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        gui.setContainer(dialogContainer);
        gui.showWelcomeMessage();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        // We can do this since we (1) Defined userInput in MainWindow.fxml (2) gave userInput the @FXML tag
        userInput.clear();
    }
}
