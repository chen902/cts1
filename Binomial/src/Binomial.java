
public class Binomial {

	public static void main(String[] args) {
		System.out.println(binomial(5,2));

	}
	
	public static int binomial(int n, int k) {
		if(k == 0 || n == k) {
			return 1;
		}
		else {
			return binomial(n - 1, k - 1) + binomial(n-1, k);
		}
	}
}
