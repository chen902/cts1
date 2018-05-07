public class Palindrome {

	public static void main(String[] args) {
		String userInput = Terminal.getString("Please enter a string to check if it is a Palindrome or not: ");
		
		if(isPalindrome(userInput)) {
			System.out.println(userInput + " is a Palindrom!");
		}
		else {
			System.out.println(userInput + " is NOT a Palindrome :(");
		}
	}
	
	private static boolean isPalindrome(String input) {
		String inversedInput = new StringBuilder(input).reverse().toString().toLowerCase().trim().replaceAll("\\s", "");
		String normalizedInput = input.toLowerCase().trim().replaceAll("\\s", "");
		
		return inversedInput.equals(normalizedInput);
	}

}
