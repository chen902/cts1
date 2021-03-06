import java.util.Date;

public class Main {

	public static void main (String[] args) {
		int[] sizes = {1000, 10000, 100000, 200000};
		
		
		for(int i=0; i<sizes.length; i++) {
			int[] values = new int[sizes[i]];

			IntArrayMethods.randomlyInitialize(values, Integer.MAX_VALUE);

			System.out.println("Sorting " + values.length + " items...");

			long beginTimestamp = System.currentTimeMillis();
			
			IntArrayMethods.insertionSort(values);
			
			long endTimestamp = System.currentTimeMillis();

			// sort the array
			System.out.println("Sorting took " + (endTimestamp - beginTimestamp)/1000.0 +  " seconds!");	

//			System.out.println("Smallest number is: " + values[0]);
//			System.out.println("Largest number is: " + values[values.length - 1]);
		}
	}
}
