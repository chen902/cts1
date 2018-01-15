
public class CommonDivisor {

	public static void main(String[] args) {
		int i1 = 0x7f;
		int i2 = 0x9;
		int r;
		
		do {
			r=i1%i2;
			i1=i2;
			i2=r;
			
		}while(i2!=0);
		System.out.println(i1);
	}

}
