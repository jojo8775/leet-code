package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer n, find a sequence that satisfies all of the following:

The integer 1 occurs once in the sequence.
Each integer between 2 and n occurs twice in the sequence.
For every integer i between 2 and n, the distance between the two occurrences of i is exactly i.
The distance between two numbers on the sequence, a[i] and a[j], is the absolute difference of their indices, |j - i|.

Return the lexicographically largest sequence. It is guaranteed that under the given constraints, there is always a solution.

A sequence a is lexicographically larger than a sequence b (of the same length) if in the first position where a and b differ, sequence a has a number greater than the corresponding number in b. For example, [0,1,9,0] is lexicographically larger than [0,1,5,6] because the first position they differ is at the third number, and 9 is greater than 5.

 

Example 1:

Input: n = 3
Output: [3,1,2,3,2]
Explanation: [2,3,2,1,3] is also a valid sequence, but [3,1,2,3,2] is the lexicographically largest valid sequence.
Example 2:

Input: n = 5
Output: [5,3,1,4,3,5,2,4,2]
 

Constraints:

1 <= n <= 20
Seen this question in a real interview before?
1/5
Yes
No
Accepted
74.2K
Submissions
103.4K
Acceptance Rate
71.8%
 * 
 * Feb 16, 2025 - 9:16:11 AM Jojo
 */
public class ConstructTheLexicographicallyLargestValidSeqence {

	public int[] constructDistancedSequence(int n) {
		int[] arr = new int[(n * 2) - 1];
		Arrays.fill(arr, -1);

		backtrack(arr, 0, n, new HashSet<>());

		return arr;
	}

	private boolean backtrack(int[] arr, int idx, int n, Set<Integer> visited) {
		if (idx == arr.length) {
			return true;
		}

		if (arr[idx] != -1) {
			return backtrack(arr, idx + 1, n, visited);
		}

		for (int i = n; i > 0; i--) {
			if (!visited.add(i)) {
				continue;
			}

			if (i == 1) {
				arr[idx] = i;

				if (backtrack(arr, idx + 1, n, visited)) {
					return true;
				}

				arr[idx] = -1;
			} else {
				boolean canPlace = false;
				if (idx + i < arr.length && arr[idx + i] == -1) {
					canPlace = true;
				}

				if (canPlace) {
					arr[idx] = arr[idx + i] = i;

					if (backtrack(arr, idx + 1, n, visited)) {
						return true;
					}

					arr[idx] = arr[idx + i] = -1;
				}
			}

			visited.remove(i);
		}

		return false;
	}

}
