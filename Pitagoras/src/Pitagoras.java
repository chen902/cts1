
public class Pitagoras {
	public static void main(String[] args) {
		int counter = 0;
		for(int a = 1 ;a <= 100; a++) {
			for(int b = 1; b <= 100; b++) {
				for(int c = 1; c <= 100; c++) {
					if(a*a + b*b == c*c) {
						System.out.println("a: " + a + " b: " + b + " c: " + c);
						counter ++;
					}
				}
			}
		}
		
		System.out.println("found: " + counter);
	}
}



