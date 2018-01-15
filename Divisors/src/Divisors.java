
public class Divisors {
	public static void main(String[] args) {
		int number = Terminal.getInt("Please type in an Integer: ");
		
		for(int divisor = number; divisor > 0; divisor--) {
			if(number%divisor == 0) {
				System.out.println(divisor);
			}
		}
	}

}
