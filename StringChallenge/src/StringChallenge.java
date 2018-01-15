
public class StringChallenge {
	public static void main(String[] args) {
		String s1 = "Ours not to reason why,";
		String s2 = "Ours but to do and die.";
		String s3 = s1 + '\n' + s2;
		System.out.println(s3.substring(18, 20));
		System.out.println(s1.charAt(12) + "" + s2.charAt(12));
		System.out.println(s1.indexOf("o"));
		System.out.println(s1.substring(s1.length()-8, s1.length()-3));
		System.out.println("" + (Integer.parseInt("47") + Integer.parseInt("11")));
		System.out.println(("" + Integer.parseInt("47")) + Integer.parseInt("11"));
		}

}
