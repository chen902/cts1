import java.util.InputMismatchException;
import java.util.Scanner;

public class EvenOrOdd {
	public static void main(String[] args){
		int number = 0; 
//		System.out.println("Please type in a number to check if it is even or odd:");
//		Scanner scanner = new Scanner(System.in);
		
		
//		try {
			number = Terminal.getInt("Please type in a number to check if it is even or odd:");
			if (number%2 == 0) {
				System.out.println(number + " is an even number!");
			}
			else {
				System.out.println(number + " is an odd number!");
			}

		} 
//		catch (InputMismatchException e) {
//				System.out.println("Please type only numbers!");
//		}
//		finally {
//				scanner.close();
//		}
//	}
		
}

