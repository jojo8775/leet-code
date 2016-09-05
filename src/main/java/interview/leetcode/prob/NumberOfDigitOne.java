package interview.leetcode.prob;

public class NumberOfDigitOne {
	public int count_recursive(int num) {
		// base case
		if (num <= 0) {
			return 0;
		}

		int constant = 0, position = 1, val = num;

		while (val >= 10) {
			position = position * 10;
			val = val / 10;
		}

		if (val == 1) {
			constant = num % position + 1;
		}

		else if (val > 1) {
			constant = position;
		}

		// this is same as for input 254
		// step 1 = 100 + 2(99) + (54)
		// step 2 = 100 + 2( 10 + 9(9) + (9)) + (10 + 5(9) + (4))
		// final step = 100 + 2( 10 + 9*1 + 1) + (10 + 5*1 + 1) since for number <= 9 will return 1
		// result = 100 + 2 (20) + 16 = 156 ans
		return constant + val * (count_recursive(position - 1)) + count_recursive(num % position);
	}

	/**
	 * Performs two actions 
	 * 1. computes occurrence in the lower digits 
	 * 2. computes occurrence for the current digit
	 * 		2a. if current digit == 1 then add seen so far // consider a small example like 234 and try it on a paper
	 * 		2b. if current digit > 1 then add the full cycle i.e. if current digit power is 10^2 then add 10^2
	 * e.g. if  input is 2346 and is current digit is 3 then 
	 * position = 2 which represents there are two lower digits 
	 * possition10PowerMinusOne = 10 which represents each lower digits contributes to 10
	 */
	public int count_iterative(int num) {
		int seenNumbers = 0, count = 0, position = 0, position10Pow = 1;

		while (num > 0) {
			int lastDigit = num % 10;
			
			// used to know the power of lower digits
			int position10PowMinusOne = position10Pow / 10;
			
			// if  input is 2346 and is current digit is 3 then 
			// position = 2 which represents there are two lower digits 
			// possition10PowerMinusOne = 10 which represents each lower digits contributes to 10
			count += lastDigit * position * position10PowMinusOne;

			if (lastDigit == 1) {
				count += seenNumbers + 1;
			} 
			else if (lastDigit > 1) {
				count += position10Pow;
			}

			seenNumbers = seenNumbers + (lastDigit * position10Pow);
			position++;
			position10Pow *= 10;
			num /= 10;
		}

		return count;
	}

	int countDigitOne(int n) {
		// base case
		if (n <= 0) {
			return 0;
		}

		int position = 1;
		int num = n;
		int constant = 0;
		while (num >= 10) {
			num = num / 10;
			position = position * 10;
		}
		if (num > 1) {
			constant = position;
		} else {
			constant = n % position + 1;
		}
		System.out.println("Constant : " + constant + " + " + num + "*" + "recurCall(" + (position - 1)
				+ ") + remaining constant: " + (n % position));
		return constant + num * countDigitOne(position - 1) + countDigitOne(n % position);
	}

	public int count2sI(int num) {

		int countof2s = 0, digit = 0;
		int j = num, seendigits = 0, position = 0, pow10_pos = 1;
		/*
		 * maintaining this value instead of calling pow() is an 6x perf gain
		 * (48s -> 8s) pow10_posMinus1. maintaining this value instead of
		 * calling Numof2s is an 2x perf gain (8s -> 4s). overall > 10x speedup
		 */
		while (j > 0) {
			digit = j % 10;
			int pow10_posMinus1 = pow10_pos / 10;
			countof2s += digit * position * pow10_posMinus1;
			/*
			 * we do this if digit <, >, or = 2 Digit < 2 implies there are no
			 * 2s contributed by this digit. Digit == 2 implies there are 2 *
			 * numof2s contributed by the previous position + num of 2s
			 * contributed by the presence of this 2
			 */
			if (digit == 2) {
				countof2s += seendigits + 1;
			}
			/*
			 * Digit > 2 implies there are digit * num of 2s by the prev.
			 * position + 10^position
			 */
			else if (digit > 2) {
				countof2s += pow10_pos;
			}
			seendigits = seendigits + pow10_pos * digit;
			pow10_pos *= 10;
			position++;
			j = j / 10;
		}
		return (countof2s);
	}

	public static void main(String[] args) {
		System.out.println(new NumberOfDigitOne().count_iterative(254));
		System.out.println(new NumberOfDigitOne().count_recursive(254));
	}
}
