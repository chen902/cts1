
public class Prime {

	public static void main(String[] args) {
		
		int number = Terminal.getInt("Please enter a positive number: ");
		int divisor = 2;

		while(number > 1) {
			if (number%divisor == 0) {
				System.out.println(divisor);
				number = number/divisor;
			}
			else {
				divisor++;
			}
		}
	}

}
