package interview.leetcode.prob;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding
 * characters in front of it. Find and return the shortest palindrome you can
 * find by performing this transformation.
 * 
 * For example:
 * 
 * Given "aacecaaa", return "aaacecaaa".
 * 
 * Given "abcd", return "dcbabcd".
 * 
 * @author jojo
 *
 */
public class ShortestPalindrom {
	public String shortestPalindrome(String s) {
		// reverse the string
		StringBuilder sb1 = new StringBuilder(s);
		sb1.reverse();

		// append it to the original
		String temp = s + "#" + sb1.toString();

		// find the max prefix == suffix
		int[] arr = new int[temp.length()];

		for (int i = 1, j = 0; i < arr.length; i++) {
			while (temp.charAt(j) != temp.charAt(i) && j != 0) {
				j = arr[j - 1];
			}

			if (temp.charAt(j) == temp.charAt(i)) {
				arr[i] = j + 1;
				j++;
			}
		}

		// getting the substring which needs to be add infornt
		return sb1.substring(0, sb1.length() - arr[arr.length - 1]) + s;
	}

	public static void main(String[] args) {
		System.out.println(new ShortestPalindrom().shortestPalindrome("aabba"));
	}
}
