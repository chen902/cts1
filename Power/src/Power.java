
public class Power {

	public static void main(String[] args) {
		int base = Terminal.getInt("Please type in an integer base: ");
		int exponent = Terminal.getInt("Please type in an integer exponent: ");
		
		Terminal.put("Result is: " + power(base, exponent));
	}
	
	private static int power(int base, int exponent) {
		int result=1;

		for(int i=0; i<exponent; i++) {
			result *= base;
		}
		
		return result;
	}
}
