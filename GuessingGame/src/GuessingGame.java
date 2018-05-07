import java.util.Random;

public class GuessingGame {

	public static void main(String[] args) {
		int secretNumber = new Random().nextInt(100) + 1;
		int numOfRounds = 1;
		int userGuess;
		long startTime; 
		
		do {
			startTime = System.currentTimeMillis();
			userGuess = Terminal.getInt("Please guess a number between 1-100: ");
			while(userGuess != secretNumber) {
				
				if(userGuess < secretNumber) {
					System.out.println("Too small!");
				}
				if(userGuess > secretNumber) {
					System.out.println("Too big!");
				}
				
				userGuess = Terminal.getInt("Please try again:  ");
				numOfRounds++;
			}
			// Gets the difference in seconds between time when we started and time when guess was successful
			double roundDuration = (System.currentTimeMillis() - startTime)/1000.0;
			
			System.out.println("Got it!" 
							   +"\n" + "Then secret number is: " + secretNumber
							   +"\n" + "You guessed in " + numOfRounds + " rounds"
							   +"\n" + "It took you: " + String.valueOf(roundDuration) + " seconds");
			
		} while(Terminal.getString("Do you wish to play again? (\"yes/no\")").toLowerCase().trim().equals("yes"));
		
		System.out.println("OK, bye-bye then!");
	}
}
