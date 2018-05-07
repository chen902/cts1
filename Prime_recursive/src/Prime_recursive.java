
public class Prime_recursive {
	private static int divisor = 2;
	
	public static void main(String[] args) {
		int result = divide(90);
	}
	
	private static int divide(int number) {
		if (number != 1) {
			if(number%divisor == 0) {
				System.out.println(divisor);
				divide(number/divisor);
			}
			else {
				divisor++;
				divide(number);
			}
		}
		else {
			return 1;
		}
		return number;
	}
}
