package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].
 * @author jojo
 *Mar 25, 20172:15:53 PM
 */
public class KDiffPairInAnArray {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }

        Set<String> visitedPairs = new HashSet<String>();
        Set<Integer> numbers = new HashSet<Integer>();

        int count = 0;
        for (int i = 0; i < nums.length; i++) {

            if (numbers.contains(nums[i] - k)) {
                int num = nums[i] - k;
                if (visitedPairs.add(num + "-" + nums[i])) {
                    count++;
                    visitedPairs.add(nums[i] + "-" + num);
                }
            }

            if (numbers.contains(nums[i] + k)) {
                int num = nums[i] + k;
                if (visitedPairs.add(num + "-" + nums[i])) {
                    count++;
                    visitedPairs.add(nums[i] + "-" + num);
                }
            }

            numbers.add(nums[i]);
        }

        return count;
    }

    public int findPair_Alt(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : arr) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                if (entry.getValue() >= 2) {
                    count++;
                }
            } else {
                if (map.containsKey(entry.getKey() + k)) {
                    count++;
                }
            }
        }

        return count;
    }
}
