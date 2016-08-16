package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 * 
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * Note: The result may be very large, so you need to return a string instead of
 * an integer.
 * 
 * 
 * @author jojo
 *
 */
public class LargestNumber {
	public String largestNumber(int[] nums) {
		String[] strArr = new String[nums.length];

		for (int i = 0; i < nums.length; i++) {
			strArr[i] = String.valueOf(nums[i]);
		}

		Arrays.sort(strArr, (String a, String b) -> {
			String s1 = a + b;
			String s2 = b + a;

			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i) != s2.charAt(i)) {
					return s2.charAt(i) - s1.charAt(i);
				}
			}

			return 0;
		});

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strArr.length; i++) {
			if (sb.length() == 0 && Long.valueOf(strArr[i]) == 0L) {
				continue;
			}

			sb.append(strArr[i]);
		}

		return sb.length() == 0 ? sb.append("0").toString() : sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(new LargestNumber().largestNumber(new int[] { 4, 90 }));
	}
}
