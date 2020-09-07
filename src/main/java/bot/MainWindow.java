package bot;

import bot.util.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
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

    private Bot duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Momoa.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Keanu.png"));
    private Image bgImage = new Image(this.getClass().getResourceAsStream("/images/bg2.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Background bg = new Background(new BackgroundImage(bgImage, null, null , null , null));
        this.dialogContainer.setBackground(bg);

    }

    public void setDuke(Bot d) {
        duke = d;
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog("ff", dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}