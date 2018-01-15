import java.util.Random;

public class Rand_average {

	public static void main(String[] args) {
		int sum = 0;
		int amountOfNumbers = 100;

		for(int i=0; i<amountOfNumbers; i++) {
			Random generator = new Random();
			sum += generator.nextInt(amountOfNumbers + 1);
		}
		
		System.out.println(sum/amountOfNumbers);
	}
}
