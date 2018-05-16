package application;

import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.scene.control.*;
import javafx.event.*;
import java.io.File;

import foundation.GOLBoard;
import foundation.GOLFileException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GOLControllerPane extends VBox {

    private static final int BUTTONWIDTH = 60;

    private GOLBoard currentBoard = new GOLBoard();
    private Timeline timeline = null;
    private Button btnStart = null;
    private Button btnPause = null;
    private GOLCanvas canvas = null;
    private File configFile = null;
    
    public GOLControllerPane(GOLCanvas canvas) {
        super(10);
        
        this.canvas = canvas;
        
        timeline = new Timeline(new KeyFrame(Duration.millis(100), ae -> animate(canvas)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        // Create the button. 
        Button btnLoad = new Button("Load");
        btnLoad.setMinWidth(BUTTONWIDTH);

        btnStart = new Button("Start");
        btnStart.setMinWidth(BUTTONWIDTH);

        btnPause = new Button("Pause");
        btnPause.setMinWidth(BUTTONWIDTH);
        btnPause.setDisable(true);

        Button btnReset = new Button("Reset");
        btnReset.setMinWidth(BUTTONWIDTH);
        
        btnReset.setOnAction((ActionEvent event) -> {
            if(configFile != null){
                loadFromFile(configFile);
                btnPause.fire();
            }
        });
        
        btnStart.setOnAction((ActionEvent event) -> {
            btnStart.setDisable(true);
            btnPause.setDisable(false);
            timeline.play();
        });
        
        btnPause.setOnAction((ActionEvent event) -> {
            btnPause.setDisable(true);
            btnStart.setDisable(false);
            timeline.pause();
        });

        // set the handler of the ActionEvent to select a GOL file
        btnLoad.setOnAction((ActionEvent ae) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("GOL", "*.gol"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            
            configFile = fileChooser.showOpenDialog(btnLoad.getScene().getWindow());
            if (configFile == null) {
                return;
            }
            
            loadFromFile(configFile);
        });

        // and then reconstruct the 'currentBoard' with the 
        // filename selected with appropriate handling of GOLFileException (Alert)
        // Add them to the box. 
        getChildren().addAll(btnLoad,btnStart,btnPause,btnReset);
        
        canvas.show(currentBoard);
    }
    
    private void loadFromFile(File file){
            try {
                // Initialize the board using a file
                currentBoard = new GOLBoard(file.getAbsolutePath());
                
                // Draw canvas according to the new board
                canvas.show(currentBoard);
                
            } catch (GOLFileException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            }        
    }
    
    private void animate(GOLCanvas canvas){
                GOLBoard next = new GOLBoard(currentBoard);
                if(next.equals(currentBoard)){
                    btnPause.fire();
                    //System.out.println("End!");
                    return;
                }
                currentBoard = next;
                canvas.show(next);                
    }

}
