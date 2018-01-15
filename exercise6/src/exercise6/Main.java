package exercise6;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 for (int i = 1; i <= 10; ++i)
			 if (fct(i)) System.out.println(i);
	}
	
	public static boolean fct(int a) {
		 if (a > 1)
		 return fct(a-2);
		 return a == 0;
		}

}
