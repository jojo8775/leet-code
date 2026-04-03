package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array arr, return the number of distinct bitwise ORs of all the non-empty subarrays of arr.

The bitwise OR of a subarray is the bitwise OR of each integer in the subarray. The bitwise OR of a subarray of one integer is that integer.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: arr = [0]
Output: 1
Explanation: There is only one possible result: 0.
Example 2:

Input: arr = [1,1,2]
Output: 3
Explanation: The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].
These yield the results 1, 1, 2, 1, 3, 3.
There are 3 unique values, so the answer is 3.
Example 3:

Input: arr = [1,2,4]
Output: 6
Explanation: The possible results are 1, 2, 3, 4, 6, and 7.
 

Constraints:

1 <= arr.length <= 5 * 104
0 <= arr[i] <= 109
 
Seen this question in a real interview before?
1/5
Yes
No
Accepted
133,908/235.7K
Acceptance Rate
56.8%
 * 
 * chiranjeebnandy
 * Apr 2, 2026  2026  9:20:46 PM
 */
public class BitwiseOrsOfSubarray {
	public int subarrayBitwiseORs(int[] A) {
        Set<Integer> uniqueSubSets = new HashSet<>();
        Set<Integer> prevSets = new HashSet<>(); 

        for(int cur : A){
            Set<Integer> endingInCur = new HashSet<>();

            for(int prev : prevSets){
                endingInCur.add(prev | cur);
            }
            
            endingInCur.add(cur);

            prevSets = endingInCur;

            uniqueSubSets.addAll(prevSets);
        }

        return uniqueSubSets.size();
    }
}
