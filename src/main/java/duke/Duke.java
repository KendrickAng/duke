package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.gui.DialogBox;
import duke.gui.Gui;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main Driver class housing the infinite loop.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Gui gui;

    public Duke(String filePath) {
        this.gui = new Gui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = storage.readFromDisk(); // leave index 0 empty for clarity
        } catch (DukeIoException e) {
            // ui.showError(e);
            this.taskList = new TaskList(); // only load the taskList if no error
        }
    }

    // JavaFX GUI won't run without this.
    public Duke() {
        this("data/duke.txt");
        //this.run();
    }

    /**
     * The main loop for Duke.
     */
    public void run() {
        //gui.showWelcomeMessage();
//        ui.showWelcomeMessage();
//        boolean isExit = false;
//
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line
//                Command c = Parser.parse(fullCommand);
//                c.execute(taskList, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e);
//            } finally {
//                ui.showLine();
//            }
//        }
    }

//    public static void main(String[] args) {
//        new Duke("data/duke.txt").run();
//    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
