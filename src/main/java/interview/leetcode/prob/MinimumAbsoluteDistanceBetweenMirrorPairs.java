package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array nums.

A mirror pair is a pair of indices (i, j) such that:

0 <= i < j < nums.length, and
reverse(nums[i]) == nums[j], where reverse(x) denotes the integer formed by reversing the digits of x. Leading zeros are omitted after reversing, for example reverse(120) = 21.
Return the minimum absolute distance between the indices of any mirror pair. The absolute distance between indices i and j is abs(i - j).

If no mirror pair exists, return -1.

 

Example 1:

Input: nums = [12,21,45,33,54]

Output: 1

Explanation:

The mirror pairs are:

(0, 1) since reverse(nums[0]) = reverse(12) = 21 = nums[1], giving an absolute distance abs(0 - 1) = 1.
(2, 4) since reverse(nums[2]) = reverse(45) = 54 = nums[4], giving an absolute distance abs(2 - 4) = 2.
The minimum absolute distance among all pairs is 1.

Example 2:

Input: nums = [120,21]

Output: 1

Explanation:

There is only one mirror pair (0, 1) since reverse(nums[0]) = reverse(120) = 21 = nums[1].

The minimum absolute distance is 1.

Example 3:

Input: nums = [21,120]

Output: -1

Explanation:

There are no mirror pairs in the array.

 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109​​​​​​​
 
Seen this question in a real interview before?
1/6
Yes
No
Accepted
116,104/196.2K
Acceptance Rate
59.2%
 * 
 * chiranjeebnandy
 * Jun 30, 2026  2026  10:42:14 PM
 */
public class MinimumAbsoluteDistanceBetweenMirrorPairs {
	public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int result = Integer.MAX_VALUE;

        for(int i=0; i<nums.length; i++){
            int reverse = reverseV2(nums[i]);
            // System.out.println("Reverse: " + reverse + "  actual: " + nums[i]);

            if(map.containsKey(nums[i])){
                // System.out.println(" reverse found ");
                result = Math.min(result, i - map.get(nums[i]));
            }

            map.put(reverse, i);
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int reverse(int n){
        StringBuilder sb = new StringBuilder();
        sb.append(n);
        
        return Integer.valueOf(sb.reverse().toString());
    }

    private int reverseV2(int n){
        int result = 0;

        while(n > 0){
            result *= 10;
            result += (n % 10);
            n /= 10;
        }

        return result;
    }
}
