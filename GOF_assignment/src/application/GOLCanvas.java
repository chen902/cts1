package application;

import javafx.scene.canvas.*;
import javafx.scene.paint.*;

import foundation.CellState;
import foundation.GOLBoard;

public class GOLCanvas extends Canvas {

    private static final int CELLSIZE = 7;
    private static final int GAP = 2;

    private GraphicsContext gc = null;

    public GOLCanvas() {
        super(GOLBoard.CELLSHORIZONTAL * (CELLSIZE + GAP) + GAP, GOLBoard.CELLSVERTICAL * (CELLSIZE + GAP) + GAP);

        // Get the graphics context for the canvas & clear. 
        gc = getGraphicsContext2D();
        clear();
    }

    // shows the contents of 'board' on the canvas
    public void show(GOLBoard board) {
        // clearing board before drawing new content
        clear();

        // Loop through the board and draw any live cells
        for (int y = 0; y < GOLBoard.CELLSVERTICAL; y++) {
            for (int x = 0; x < GOLBoard.CELLSHORIZONTAL; x++) {
                if (board.getCellState(x, y) == CellState.LIVE) 
                    // offseting to the canvas coordinates is achieved by 'cell_index*(overallsize)'
                    gc.fillRect(x * (CELLSIZE + GAP), y * (CELLSIZE + GAP), CELLSIZE, CELLSIZE);
            }
        }
    }

    public void clear() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setStroke(Color.LIGHTSLATEGRAY);
        gc.strokeRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

}
