public class TTerminal {
	public static void main(String[] args) {
		
		int i = Terminal.getInt("Please enter an integer number:");
		Terminal.put(i + " power 2 = " + (i * i));
		
		double d = Terminal.getDouble("Please enter a decimal fraction:");
		Terminal.put(d + " power 2 = " + (d * d));

		String s = Terminal.getString("Do you like that?");
		Terminal.put(s.toLowerCase().equals("yes") ? "Good!" : "Pity!");
		
	}
}
