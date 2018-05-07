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
            System.out.println(word + " Accepted!");

        word = "5.432";

        if (FloatDFA.accept(word)) 
            System.out.println(word + " Accepted!");
        word = "123.4500";

        if (FloatDFA.accept(word)) 
            System.out.println(word + " Accepted!");
        word = "2.345e6";

        if (FloatDFA.accept(word)) 
            System.out.println(word + " Accepted!");
        word = "0.20E+678";

        if (FloatDFA.accept(word)) 
            System.out.println(word + " Accepted!");
        word = "1004.5e-6789";

        if (FloatDFA.accept(word)) 
            System.out.println(word + " Accepted!");

        word = "0.4325ef66";
        if (FloatDFA.accept(word)) 
            System.out.println(word + " Accepted!");

        word = "12.4325e+f66";
        if (FloatDFA.accept(word)) 
            System.out.println(word + " Accepted!");
        
        word = ".432";
        if (FloatDFA.accept(word)) 
            System.out.println(word + " Accepted!");

        word = "12.e";
        if (FloatDFA.accept(word)) 
            System.out.println(word + " Accepted!");

        word = "12.5e+";
        if (FloatDFA.accept(word)) 
            System.out.println(word + " Accepted!");
    }

    public static boolean accept(String word) {
        int position = 0;
        int state = 0;  // starting state

        while (position < word.length()) {
            char ch = word.charAt(position);
            position++;
            switch (state) {
                case 0:
                    // function [0-9] -> state 1
                    if (Character.isDigit(ch)) {     
                        state = 1;
                    } else{
                        System.out.println("Error! For word: "+ word +posIndicator(position)+"Expected a digit is position: " + position);
                        return false;                        
                    }
                    break;
                case 1:
                    // function [0-9] -> state 1
                    // function '.' -> state 2
                    if(ch == '.'){
                        state = 2;
                    } else if(!Character.isDigit(ch)){
                        System.out.println("Error! For word: "+ word + posIndicator(position)+ "Expected a digit or '.' in position: " + position);
                        return false;
                    }
                    break;
                case 2:
                    // function [0-9] -> state 3
                    if(Character.isDigit(ch)){
                       state = 3; 
                    } else {
                        System.out.println("Error! For word: "+ word +posIndicator(position)+"Expected a digit is position: " + position);
                        return false;
                    }
                    break;
                case 3:
                    // function [0-9] -> state 3
                    // function e,E -> state 4
                    if (Character.toLowerCase(ch) == 'e') {
                        state = 4;
                    } else if (!Character.isDigit(ch)) {
                        System.out.println("Error! For word: "+ word + posIndicator(position)+ "Expected 'e/E' or a digit in position: " + position);
                        return false;
                    }
                    break;
                case 4:
                    // function +,- -> state 5
                    // function [0-9] -> state 6
                    if(ch == '+' || ch == '-'){
                        state = 5;
                    } else if(Character.isDigit(ch)){
                        state = 6;
                    } else{
                        System.out.println("Error! For word: "+ word + posIndicator(position)+"Expected '+/-' or a digit in position: " + position);
                        return false;                    
                    }
                    break;
                case 5:
                    // function [0-9] -> state 6
                    if (Character.isDigit(ch)) {
                        state = 6;
                    } else{
                        System.out.println("Error! For word: "+ word + posIndicator(position)+"Expected a digit in position: " + position);
                        return false;                        
                    }
                    break;
                case 6:
                    // function [0-9] -> state 6
                    // accept state
                    if(!Character.isDigit(ch)){
                        System.out.println("Error! For word: "+ word + posIndicator(position)+"Expected a digit in position: " + position);
                    }
                    break;
            }
        }
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
    
    // accept states are 3 and 6
    private static boolean isAcceptState(int state) {
        return (state == 3) || (state == 6);
    }
}
