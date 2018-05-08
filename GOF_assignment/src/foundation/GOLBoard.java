package foundation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;


public class GOLBoard {
    private static String ERROR_MESSAGE = "File is invalid!";
    public static int CELLSHORIZONTAL = 120;
    public static int CELLSVERTICAL = 120;
    public static char REPRESENTATIONLIVECELL = '+';
    public static char REPRESENTATIONDEADCELL = '.';

    public CellState[][] board = new CellState[CELLSHORIZONTAL][CELLSVERTICAL];

    public GOLBoard() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                board[i][j] = CellState.DEAD;
            }
        }
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
    public GOLBoard(String filename) throws GOLFileException{
        File file = new File(filename);
        String[] fileLines = new String[1];
        BufferedReader reader = null;
        int lineIndex = 0;

        try {
            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line;
            
            // Reading the file into a string array 
            while ((line = reader.readLine()) != null) {
                // String array is extended dynamically
                fileLines = addLine(fileLines, line);	
            }
        } catch (IOException e) {
            throw new GOLFileException(ERROR_MESSAGE);
        } finally {
            if(reader != null)
                try{
                    reader.close();
                }catch(IOException e){
                    throw new GOLFileException(ERROR_MESSAGE);
                }
        }
        
        // Check file validity and in case it is not valid throw a GOLFileException
        if (!isValid(fileLines)) {
            throw new GOLFileException(ERROR_MESSAGE);
        }
        
        //******************************************************************
        // Jason: spawn the content of the file onto the center of the board
        // hint: translate REPRESENTATIONLIVECELL and REPRESENTATIONDEADCELL to CellState in the right places on the 'board'
        // Centering formula = (width_conf_file - width_board) / 2
        //			(height_conf_file - height_board) / 2
        //******************************************************************
    }

    // Implementing dynamic add of strings to a string array
    private String[] addLine(String[] file, String newLine) {
        String[] temp;
        // A temp array 1 string larger than the original is created 
        if (file[file.length - 1] != null) {
            temp = new String[file.length + 1];
            for (int index = 0; index < file.length; index++) {
                temp[index] = file[index];
            }
            temp[temp.length - 1] = newLine;
            file = temp;
        } else {
            file[file.length - 1] = newLine;
        }
        return file;
    }

    // validates the board file, returns false in any case of an unsupported element
    private boolean isValid(String[] board) {
        // Checks if file is not empty, and that it does not exceed the allowed dimensions
        if(board.length == 0 || board.length > CELLSVERTICAL || board[0].length() > CELLSHORIZONTAL)
            return false;
        
        // Checks if line lengths vary
        for (int i = 0; i < board.length - 1; i++) {
            if (board[i].length() != board[i + 1].length())	// if length of current line varies from the length of the next one then not valid
                return false;
        }
                
        // Verifying that all lines contain valid characters using a regular expression
        for (String line : board) {
            // regex = /[+.]+/
            String regex = "[" + REPRESENTATIONLIVECELL + REPRESENTATIONDEADCELL + "]" + "+";
            if (!Pattern.matches(regex, line))
                return false;
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
