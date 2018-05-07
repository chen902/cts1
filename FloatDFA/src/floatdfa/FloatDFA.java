// Theoretical CTS - Exercise sheet 2
// Exercise 3.9 c) - java implementation, DFA for a float number
// Authors:
//  Leonhard Szerejko
//  Chen Kasirer
// public static boolean accept(String word)
//  parameters:
//      (String) word = a string to match
//  returns (Boolean):
//      true: if word is compatible with a float number 
//      false: in case of incompatibility
//  output to java console:
//      in case of an error, the location and type of error is printed.
//***********************************************************************
package floatdfa;

public class FloatDFA {

    // Test cases
    public static void main(String[] args) {
        String word = "12.4325e+66";
        if (FloatDFA.accept(word)) 
            System.out.println("Accepted!");

        word = "12.4325ef66";
        if (FloatDFA.accept(word)) 
            System.out.println("Accepted!");

        word = "12.4325e+f66";
        if (FloatDFA.accept(word)) 
            System.out.println("Accepted!");
        
        word = ".432";
        if (FloatDFA.accept(word)) 
            System.out.println("Accepted!");

        word = "12.e";
        if (FloatDFA.accept(word)) 
            System.out.println("Accepted!");

        word = "12.5e+";
        if (FloatDFA.accept(word)) 
            System.out.println("Accepted!");

        word = "12.432";
        if (FloatDFA.accept(word)) 
            System.out.println("Accepted!");
    }

    public static boolean accept(String word) {
        int position = 0;
        int state = 0;  // starting state

        while (position < word.length()) {
            char ch = word.charAt(position);
            position++;
            switch (state) {
                case 0:
                    // function [0-9] = state 0
                    // function '.' = state 1
                    switch (ch) {
                        case '.':           // function '.'
                            state = 1;
                            break;
                        default:
                            if (!Character.isDigit(ch)) {     // function '[0-9]'
                                System.out.println("Error! For word: "+ word +posIndicator(position)+"Expected '.' or a digit is position: " + position);
                                return false;
                            }
                    }
                    break;
                case 1:
                    // function [0-9] = state 2
                    if (Character.isDigit(ch)) {      // function '[0-9]' 
                        state = 2;
                    } else {
                        System.out.println("Error! For word: "+ word + posIndicator(position)+ "Expected a digit in position: " + position);
                        return false;
                    }
                    break;
                case 2:
                    // function [0-9] = state 2
                    // function e,E = state 3
                    if (Character.toLowerCase(ch) == 'e') {
                        state = 3;
                    } else if (!Character.isDigit(ch)) {
                        System.out.println("Error! For word: "+ word + posIndicator(position)+ "Expected 'e/E' or a digit in position: " + position);
                        return false;
                    }
                    break;
                case 3:
                    // function +,-,[0-9] = state 4
                    if(ch == '+' || ch == '-' || Character.isDigit(ch))
                        state = 4;
                    else{
                        System.out.println("Error! For word: "+ word + posIndicator(position)+"Expected '+/-' or a digit in position: " + position);
                        return false;                    
                    }
                    break;
                case 4:
                    // function [0-9] = state 4
                    // accept state 
                    if (!Character.isDigit(ch)) {
                        System.out.println("Error! For word: "+ word + posIndicator(position)+"Expected a digit in position: " + position);
                        return false;
                    }
                    break;
            }
        }
        //System.out.println("Ended in state " + state);
        return isAcceptState(state);
    }
    
    // Generates a nice arrow to indicate the first(!) faulty character
    private static String posIndicator(int position){
        String result = "                ↑\n";
        for(int i=0; i<position; i++){
            result = " " + result;
        }
        return "\n" + result;
    }
    
    // accept states are 2 and 4
    private static boolean isAcceptState(int state) {
        return (state == 2) || (state == 4);
    }
}
