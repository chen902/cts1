
public class Factorial {

	public static void main(String[] args) {
		System.out.println(factorial(3));
	}
	
	public static int factorial(int number) {
		if(number == 0) {
			return 1;
		}
		else {
			return number * factorial(number-1);
		}
	}

}
