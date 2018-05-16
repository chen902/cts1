package foundation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class GOLBoard {

    public static final int CELLSHORIZONTAL = 80;
    public static final int CELLSVERTICAL = 80;
    public static final char REPRESENTATIONLIVECELL = '+';
    public static final char REPRESENTATIONDEADCELL = '.';

    private String error_message;

    public CellState[][] board = new CellState[CELLSHORIZONTAL][CELLSVERTICAL];

    // Default constructor, initializes an all-dead cells board
    public GOLBoard() {
        clearBoard(board);
    }

    // Initializes all cells of the board to be 'DEAD'
    private void clearBoard(CellState[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                board[i][j] = CellState.DEAD;
            }
        }
    }

    public boolean equals(GOLBoard board){
        boolean isEqual = true;
        for(int y=0; y<CELLSVERTICAL; y++){
            for(int x=0; x<CELLSHORIZONTAL; x++){
                if(this.board[x][y] != board.getCellState(x, y)){
                    isEqual = false;
                }
            }
        }
        return isEqual;
    }
    private int numOfNeighbors(GOLBoard board, int x, int y) {
        //  (-1,-1)(0,-1)(+1,-1)
        //  (-1,0) (skip)(+1,0)
        //  (-1,+1)(0,+1)(+1,+1)
        int result = 0;
        int[] offsets = {-1, 1, 0, 1, 1, 1, -1, 0, 1, 0, -1, -1, 0, -1, 1, -1};
        for (int i = 0, o = 0; i < 8; i++) {
            // checks that neighbors indexes are within board boundaries
            if (x + offsets[o] >= 0
                    && x + offsets[o] < CELLSHORIZONTAL
                    && y + offsets[o + 1] >= 0
                    && y + offsets[o + 1] < CELLSVERTICAL) {
                if (board.getCellState(x + offsets[o], y + offsets[o + 1]) == CellState.LIVE) {
                    result++;
                }
            }
            o = o + 2;
        }
        //  (x-1,y-1)(x,y-1)(x+1,y-1)
        //  (x-1,y)  (x,y)  (x+1,y)
        //  (x-1,y+1)(x,y+1)(x+1,y+1)
        return result;
    }

    public GOLBoard(GOLBoard previous) {
        CellState[][] nextBoard = new CellState[CELLSHORIZONTAL][CELLSVERTICAL];
        clearBoard(nextBoard);
        for (int indexY = 0; indexY < CELLSVERTICAL; indexY++) {
            for (int indexX = 0; indexX < CELLSHORIZONTAL; indexX++) {
                int neighbors = numOfNeighbors(previous, indexX, indexY);
                //System.out.print(neighbors+" ");
                if (previous.getCellState(indexX, indexY) == CellState.LIVE) {
                    if (neighbors < 2 || neighbors > 3) {
                        // cell dies
                        nextBoard[indexX][indexY] = CellState.DEAD;
                    }else{
                        nextBoard[indexX][indexY] = CellState.LIVE;
                    }
                }
                if (previous.getCellState(indexX, indexY) == CellState.DEAD) {
                    if (neighbors == 3) {
                        // cell becomes alive
                        nextBoard[indexX][indexY] = CellState.LIVE;
                    }
                }
            }
            //System.out.println("");
        }
//        for(int y=0; y<CELLSVERTICAL; y++){
//            for(int x=0; x<CELLSHORIZONTAL;x++){
//                System.out.print(nextBoard[x][y]);
//            }
//            System.out.println("");
//        }
        board = nextBoard;
    }

    // loads the initial configuration from a GOL file and centers it on
    // the board
    // GOLFileException is thrown if
    // - the file cannot be opened or read
    // - the file contains illegal characters (different from
    //   REPRESENTATIONLIVECELL, REPRESENTATIONDEADCELL)
    // - the width of text lines varies
    // - the text lines have a length of 0 or > CELLSHORIZONTAL
    // - there are more lines than CELLSVERTICAL
    public GOLBoard(String filename) throws GOLFileException {
        // Initialize board 
        clearBoard(board);

        File file = new File(filename);

        // This dynamic data structor is used to store the lines of the file after reading
        // allows the program to analize the file content while only reading it from the disk once
        DynamicStringArray fileLines = new DynamicStringArray();

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line;

            // 'line' stores the current line read from the file
            // it is then added to the dynamic string array to be analyzed later
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (IOException e) {
            throw new GOLFileException(error_message);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new GOLFileException(error_message);
                }
            }
        }

        // isValid accepts the file content as a String array and checks for any predefined irregularities 
        // if any issue is found, isValid returns 'false' and a GOLFileException is thrown 
        if (!isValid(fileLines.getArray())) {
            throw new GOLFileException(error_message);
        }

        // These two variables are used to offset the indexes that are used to travers the file lines 
        // into a centered location on the board 
        int offsetX = (CELLSHORIZONTAL - fileLines.getString(0).length()) / 2;
        int offsetY = (CELLSVERTICAL - fileLines.length()) / 2;

        // Load file content into the board in a centered manner
        for (int y = 0; y < fileLines.length(); y++) {
            for (int x = 0; x < fileLines.getString(0).length(); x++) {
                if (fileLines.getString(y).charAt(x) == REPRESENTATIONLIVECELL) {
                    board[x + offsetX][y + offsetY] = CellState.LIVE;
                }
            }
        }

//        for (int y = 0; y < CELLSVERTICAL; y++) {
//            for (int x = 0; x < CELLSHORIZONTAL; x++) {
//                int neighbors = numOfNeighbors(this, x, y);
//                System.out.print(neighbors+" ");
//            }
//            System.out.println("");
//        }
    }

    // Validates the board file, returns false in any case of an unsupported element
    // 'error_message' is set in case of an error to provide description
    private boolean isValid(String[] board) {
        // Checks if file is not empty, and that it does not exceed the allowed dimensions
        if (board.length == 0) {
            error_message = "File is empty!";
            return false;
        }

        if (board.length > CELLSVERTICAL || board[0].length() > CELLSHORIZONTAL) {
            error_message = "File exceeds the allowed dimensions.";
            return false;
        }

        // Checks if line lengths vary
        for (int i = 0; i < board.length - 1; i++) {
            if (board[i].length() != board[i + 1].length()) {	// if length of current line varies from the length of the next one then not valid
                error_message = "Line lengths are not allowed to vary.";
                return false;
            }
        }

        // Verifying that all lines contain valid characters using a regular expression
        for (String line : board) {
            // regex = /[+.]+/
            String regex = "[" + REPRESENTATIONLIVECELL + REPRESENTATIONDEADCELL + "]" + "+";
            if (!Pattern.matches(regex, line)) {
                error_message = "File contains illegal characters.";
                return false;
            }
        }

        // if reached here, file is valid
        return true;
    }

    public CellState getCellState(int col, int row) {
        return board[col][row];
    }

    public void setCellState(int col, int row, CellState value) {
        board[col][row] = value;
    }

}
