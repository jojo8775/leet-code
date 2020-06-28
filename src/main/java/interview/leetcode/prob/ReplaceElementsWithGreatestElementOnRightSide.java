package interview.leetcode.prob;

/**
 * Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

After doing so, return the array.

 

Example 1:

Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]
 

Constraints:

1 <= arr.length <= 10^4
1 <= arr[i] <= 10^5
Accepted
50,375
Submissions
66,242
 * @author jojo
 * Jun 27, 2020  10:57:21 PM
 */
public class ReplaceElementsWithGreatestElementOnRightSide {
	public int[] replaceElements(int[] arr) {
		int len = arr.length;
		int max = Integer.MIN_VALUE, prev = arr[len - 1];

		for (int i = len - 2; i >= 0; i--) {
			max = Math.max(max, prev);
			prev = arr[i];
			arr[i] = max;
		}

		arr[arr.length - 1] = -1;

		return arr;
	}
}
