package interview.leetcode.prob;

/**
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
Su
 * @author jojo
 *
 */
public class NthDigit {

	public int findNthDigit_efficient(int n) {
		int length = 1;
		long range = 9;
		int start = 1;
		
		// consider an input index which belongs to 1002 number. 
		// first cycle we eliminate 1 to 9
		// second cycle we eliminate 10 to 90 + first cycle ~ 10 to 99
		// third cycle we eliminate 100 to 900 + second cycle ~ 100 to 999
		// this is the reason why just * 10 works 
		while (n > length * range) {
			// this will make sure we are removing all the characters which are
			// before start
			n -= (length * range);

			length += 1;

			// making the range ready for the next cycle
			range *= 10;

			// updating the starting point
			start *= 10;
		}

		// taking one off because at start is index 1. Dividing n with length to
		// find the number in a specific cycle.
		start += (n - 1) / length;
		String s = String.valueOf(start);
		
		// modulus gives us the digit in a number.
		return Character.getNumericValue(s.charAt((n - 1) % length));
	}

	public int findNthDigit(int n) {
		StringBuilder sb = new StringBuilder();
		int count = 1;

		while (sb.length() < n) {
			sb.append(count++);
		}

		return Character.getNumericValue(sb.charAt(n));
	}

	public static void main(String[] args) {
		System.out.println(new NthDigit().findNthDigit(100000000));
	}
}
