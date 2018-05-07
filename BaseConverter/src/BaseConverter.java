//****************************************************************************
//Course	: Programming 1 (CTS1)
//Task  	: Monitored assignment 1
//Professor : Kratzer
//Authors	: Alex Simeonova, Chen Kasirer, Jason Kiesl
//****************************************************************************
//Base Converter converts between counting bases using two main public methods
//****************************************************************************
//toString(int value, int radix)
//Parameters:
//value = an integer in base 10
//radix = an integer the destination base
//Return value: a string representing the value in the requested base
//Usage: System.out.println(BaseConverter.toString(123, 16)); -> "7b"
//****************************************************************************
//toInt(String representation, int radix)
//Parameters: 
//value = a string representation of a number
//radix = an integer representing the origin base of the given number 
//Return value: an integer representation of the given number in base 10
//Usage: System.out.println(BaseConverter.toInt("af", 16)); -> 175
//****************************************************************************
public class BaseConverter {

	// This array is used for integer-to-char and char-to-integer conversion operations.
	private final static char[] digits = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

	// TODO: add comments regarding the algorithm
	public static String toString(int value, int radix) {
		String output = "";
		int result = value;
		int reminder;

		// As long as the result portion of the division is larger/equal to the base
		// the reminder is appended to the output
		do {
			reminder = result % radix;
			result /= radix;
			output = getDigit(reminder) + output;
		}
		while (result >= radix);

		// Appending the last division result to the output
		if(result > 0) { 
			output = getDigit(result) + output;
		}
		return output;
	}

	// TODO: add comments regarding the algorithm 
	public static int toInt(String representation, int radix) {
		int result;
		// If number is NOT valid, set result to -1
		if (!checkValidity(representation, radix)) {
			result = -1;
		}
		// Otherwise, convert the number from original base 'radix' to base10
		else {
			result = 0; 
			// Starting from index (MSB) incrementing towards the LSB (end of string)
			// Exponent is initialized to the MSB exponent and decremented by one for each iteration 
			for(int index=0, exponent=representation.length()-1; index<representation.length();index++,exponent--) {
				int valueOfDigit = getDigit(representation.charAt(index));
				result += valueOfDigit *  power(radix,exponent);
			}
		}
		return result;
	}

	// This method gets the string representation of a number and a counting base and returns 'true' if
	// number is valid in the given base or 'false' otherwise
	// Negative numbers are also invalid
	private static boolean checkValidity(String representation, int radix) {
		//TODO: Implement
		boolean isValid = true;
		for(int index=0; index < representation.length() && isValid	; index++) {
			isValid = false;
			int digit = getDigit(representation.charAt(index)); // get the integer representation of the digit at index
			if(digit != -1 && digit<radix) {
				isValid = true;
			}
		}
		return isValid;
	}

	// Returns the string representation of a number between 0 and f 
	private static char getDigit(int number) {
		// 'x' represents an invalid input
		return (number <= 15 && number >= 0) ? digits[number] : 'x';
	}

	// Returns the integer representation of a string digit between 0 and f
	// if it is not valid -1 is returned
	private static int getDigit(char digit) {
		boolean found=false;
		int value = -1;
		for(int i=0; i<digits.length && !found;i++) //Stop searching when digit is found
			if(digit == digits[i]) {
				//returning the index rather than the value
				value = i; 
				found=true;
			}
	
		return value;
	}

	// Gets an integer 'base' and integer 'exponent' and returns the number to the power of exponent 
	// Implemented as part of exersice 2 of "Methods" exercise sheet
	private static int power(int base, int exponent) {
		int result=1;
		for(int i=0; i<exponent; i++) 
			result *= base;
		return result;
	}
}
