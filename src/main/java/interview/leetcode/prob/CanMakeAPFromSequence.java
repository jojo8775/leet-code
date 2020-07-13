package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of numbers arr. A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.

Return true if the array can be rearranged to form an arithmetic progression, otherwise, return false.

 

Example 1:

Input: arr = [3,5,1]
Output: true
Explanation: We can reorder the elements as [1,3,5] or [5,3,1] with differences 2 and -2 respectively, between each consecutive elements.
Example 2:

Input: arr = [1,2,4]
Output: false
Explanation: There is no way to reorder the elements to obtain an arithmetic progression.
 

Constraints:

2 <= arr.length <= 1000
-10^6 <= arr[i] <= 10^6
Accepted
14,546
Submissions
19,020
 * @author jojo
 * Jul 13, 2020  12:29:41 AM
 */
public class CanMakeAPFromSequence {
	// o(n)
	public boolean canMakeArithmeticProgression(int[] arr) {
		int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

		Set<Integer> set = new HashSet<>();

		// the the minimum two elements.
		for (int val : arr) {
			if (val <= min1) {
				min2 = min1;
				min1 = val;
			} else if (val < min2) {
				min2 = val;
			}

			set.add(val);
		}

		// finding the difference of minimum of two elements.
		int diff = min2 - min1;

		// if the input has same digits.
		if (diff == 0) {
			return set.size() == 1;
		}

		// for each element, there should be an entry of min + (index * diff)
		for (int i = 0; i < arr.length; i++) {
			if (!set.contains(min1 + (diff * i))) {
				return false;
			}
		}

		return true;
	}

	// O(nlogn)
	public boolean canMakeArithmeticProgression_easy(int[] arr) {
		Arrays.sort(arr);
		int diff = arr[0] - arr[1];
		for (int i = 2; i < arr.length; i++) {
			if (arr[i - 1] - arr[i] != diff) {
				return false;
			}
		}
		return true;
	}
}
