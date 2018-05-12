package application;

import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.scene.control.*;
import javafx.event.*;
import java.io.File;

import foundation.GOLBoard;
import foundation.GOLFileException;

public class GOLControllerPane extends VBox {

    private static final int BUTTONWIDTH = 60;

    private GOLBoard currentBoard = new GOLBoard();

    public GOLControllerPane(GOLCanvas canvas) {
        super(10);

        // Create the button. 
        Button btnLoad = new Button("Load");
        btnLoad.setMinWidth(BUTTONWIDTH);

        // set the handler of the ActionEvent to select a GOL file
        btnLoad.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("GOL", "*.gol"),
                        new FileChooser.ExtensionFilter("All Files", "*.*")
                );

                File file = fileChooser.showOpenDialog(btnLoad.getScene().getWindow());
                if (file == null) {
                    return;
                }

                try {
                    // Initialize the board using a file
                    currentBoard = new GOLBoard(file.getName());

                    // Draw canvas according to the new board
                    canvas.show(currentBoard);
                } catch (GOLFileException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
                }
            }
        });

        // and then reconstruct the 'currentBoard' with the 
        // filename selected with appropriate handling of GOLFileException (Alert)
        // Add them to the box. 
        getChildren().addAll(btnLoad);
        canvas.show(currentBoard);
    }

}
