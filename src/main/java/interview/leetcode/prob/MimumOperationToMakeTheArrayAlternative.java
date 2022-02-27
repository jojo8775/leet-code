package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed array nums consisting of n positive integers.

The array nums is called alternating if:

nums[i - 2] == nums[i], where 2 <= i <= n - 1.
nums[i - 1] != nums[i], where 1 <= i <= n - 1.
In one operation, you can choose an index i and change nums[i] into any positive integer.

Return the minimum number of operations required to make the array alternating.

 

Example 1:

Input: nums = [3,1,3,2,4,3]
Output: 3
Explanation:
One way to make the array alternating is by converting it to [3,1,3,1,3,1].
The number of operations required in this case is 3.
It can be proven that it is not possible to make the array alternating in less than 3 operations. 
Example 2:

Input: nums = [1,2,2,2,2]
Output: 2
Explanation:
One way to make the array alternating is by converting it to [1,2,1,2,1].
The number of operations required in this case is 2.
Note that the array cannot be converted to [2,2,2,2,2] because in this case nums[0] == nums[1] which violates the conditions of an alternating array.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
Accepted
8,967
Submissions
31,539
 * @author jojo
 * Feb 13, 2022 11:05:37 PM
 */
public class MimumOperationToMakeTheArrayAlternative {
	public int minimumOperations(int[] nums) {
		Map<Integer, Integer> odd = new HashMap<>(), even = new HashMap<>();
		int len = nums.length;

		for (int i = 0; i < len; i++) {
			if (i % 2 == 0) {
				even.put(nums[i], even.getOrDefault(nums[i], 0) + 1);
			} else {
				odd.put(nums[i], odd.getOrDefault(nums[i], 0) + 1);
			}
		}

		int oddKey1 = 0, oddKey2 = 0, evenKey1 = 0, evenKey2 = 0;
		odd.put(0, 0);
		even.put(0, 0);

		for (int key : odd.keySet()) {
			if (odd.get(oddKey1) < odd.get(key)) {
				oddKey2 = oddKey1;
				oddKey1 = key;
			} else if (odd.get(oddKey2) < odd.get(key)) {
				oddKey2 = key;
			}
		}

		for (int key : even.keySet()) {
			if (even.get(evenKey1) < even.get(key)) {
				evenKey2 = evenKey1;
				evenKey1 = key;
			} else if (even.get(evenKey2) < even.get(key)) {
				evenKey2 = key;
			}
		}

		// if the requrement was just nums[i] == nums[i-2] then the result would have
		// been (len - even.get(evenKey1) - odd.get(oddKey1);)
		// however, there is another requirement nums[i] != nums[i-1], for this reason
		// if the max freq key of odd and even are equal then we need
		// to do this (len - Math.max(even.get(evenKey1) + odd.get(oddKey2),
		// odd.get(oddKey1) + even.get(evenKey2));)

		if (evenKey1 == oddKey1) {
			return len - Math.max(even.get(evenKey1) + odd.get(oddKey2), odd.get(oddKey1) + even.get(evenKey2));
		}

		return len - even.get(evenKey1) - odd.get(oddKey1);
	}
}
