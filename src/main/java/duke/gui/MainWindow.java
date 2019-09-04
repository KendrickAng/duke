package duke.gui;

import duke.Duke;
// the @FXML notation marks a private or protected member, allowing FXML to access it despite the modifier.
import duke.Parser;
import duke.command.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        // initialize gui
        gui.setContainer(this.dialogContainer);
        gui.setInput(this.userInput);
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
        String input = gui.readCommand();
        Command command = Parser.parse(input);
        if (command.isExit()) {
            // Hacky way to get back the primary stage
            Stage primaryStage = (Stage) scrollPane.getScene().getWindow();
            primaryStage.fireEvent(new WindowEvent(
                            primaryStage,
                            WindowEvent.WINDOW_CLOSE_REQUEST
                    ));
        } else {
            // Log the given command
            dialogContainer.getChildren().add(
                    DialogBox.getUserDialog(input, userImage)
            );
            // We can do this since we (1) Defined userInput in MainWindow.fxml (2) gave userInput the @FXML tag
            System.out.println("FDSFJSLFIJESLFEKFLE");
            // prints to gui too
            System.out.println(duke.getTaskList());
            System.out.println(duke.getGui());
            System.out.println(duke.getStorage());
            command.execute(duke.getTaskList(), duke.getGui(), duke.getStorage());
            userInput.clear();

        }
    }
}
