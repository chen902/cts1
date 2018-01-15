import java.util.Scanner;
public class StringReverse {

	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		String inputString = inputScanner.nextLine();
		
		inputScanner.close();
		System.out.println(inputString);
		
		char[] helpArray = new char[inputString.length()];
		
		int inputIndex = inputString.length();
		for(int index = 0; index < inputString.length(); index++) {
			inputIndex--;
			
			helpArray[index] = inputString.charAt(inputIndex);
		}
		
		System.out.println(helpArray);
	}
}
