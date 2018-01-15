import java.util.Random;

public abstract class IntArrayMethods {

	private static Random random = new Random();
	
	public static void initialize(int[] arr, int initialValue) {
		// initializes all components of arr with initialValue
		for (int i = 0; i < arr.length; ++i)
			arr[i] = initialValue;
	}
	
	public static void randomlyInitialize(int[] arr, int upperBound) {
		// initializes all components of arr with initialValue
		for (int i = 0; i < arr.length; ++i)
			arr[i] = random.nextInt(upperBound);
	}
	
	public static int sum(int[] arr) {
		// return the sum of all components of arr
		int result = 0;
		
		for (int value : arr)
			result += value;
		
		return result;
	}

	public static boolean isSorted(int[] arr) {
		for (int i = 0; i < arr.length-1; ++i)
			if (arr[i] > arr[i+1])
				return false;
		return true;
	}
	
	public static double average(int[] arr) {
		return (1.0 * sum(arr)) / arr.length;
	}

	public static boolean contains(int[] arr, int searchValue) {
		// return whether searchValue is contained somewhere in the array
		for (int i = 0; i < arr.length; ++i)
			if (arr[i] == searchValue)
				return true;
		return false;
	}

	public static void sort(int[] arr) {
		// sorts arr (ascending)
		for (int i = 0; i < arr.length-1; ++i) {
			// search for the minimum in the subsequence starting at i
			int minimumValue = arr[i];
			int minimumIndex = i;
			for (int j = i+1; j < arr.length; ++j) {
				if (arr[j] < minimumValue) {
					minimumValue = arr[j];
					minimumIndex = j;
				}
			}
			arr[minimumIndex] = arr[i];
			arr[i] = minimumValue;
		}
	}
	
	public static String toString(int[] arr) {
		// returns a string representation of all components of arr
		String result = "";
		
		for (int i = 0; i < arr.length; ++i)
			result += arr[i] + " - ";
		
		return result.substring(0, result.length()-3);
	}
	
	
}
