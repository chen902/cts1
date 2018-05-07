
public class SquareRoot {

	public static void main(String[] args) {
		System.out.println(squareRoot(16));
	}
	
	private static double squareRoot(double d) {
		double presicion = 0.000001;
		double upper = d;
		double lower = 1.0;
		double middle = (upper + lower) / 2;
	
		while (upper - lower > presicion) {
			
			if(middle*middle < d) {
				lower = middle;
			}
			else {
				upper = middle;
			}
			middle = (upper + lower) / 2;
		}
		
		return middle;
	}
}
