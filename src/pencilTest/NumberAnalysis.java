package pencilTest;

/**
 * Methods to analyze numbers.
 * @author malai40
 */
public class NumberAnalysis {
	/**
	 * Count the number of digits in an integer.
	 * @param inputNumber A number in {@code int} format. 
	 * @return The number of digits in the integer supplied.
	 */
	public static int getNumberOfDigitsInInteger(int inputNumber) {
		/*
		 * 1/10 = .1. floor to 0 digits (or less than 1), plus 1, 1 digit.
		 * 15/10 = 1.5. floor to 1, plus 1, 2 digit.
		 * 223/10 = 22.3. floor to 22, (22 - (22 % 10)) = 20, 
		 * 10083/10 = 1008.3.
		 */
		
		int numberOfDigits = 0;
		
		while (inputNumber != 0) {
			inputNumber /= 10; /* a /= b means divide a by b and put the result in a */
			numberOfDigits++;
		}
		
		return numberOfDigits;
	}
}
