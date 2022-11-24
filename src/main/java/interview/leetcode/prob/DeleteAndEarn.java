package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:

Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
Return the maximum number of points you can earn by applying the above operation some number of times.

 

Example 1:

Input: nums = [3,4,2]
Output: 6
Explanation: You can perform the following operations:
- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
- Delete 2 to earn 2 points. nums = [].
You earn a total of 6 points.
Example 2:

Input: nums = [2,2,3,3,3,4]
Output: 9
Explanation: You can perform the following operations:
- Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
- Delete a 3 again to earn 3 points. nums = [3].
- Delete a 3 once more to earn 3 points. nums = [].
You earn a total of 9 points.
 

Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i] <= 104
Accepted
241,003
Submissions
420,268
 * @author jojo
 * Nov 24, 2022 2:19:57 PM
 */
public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        // states 
        // using the buckets to add all the duplicate elements. This can be done using a map as an alternative. 
        int[] buckets = new int[10001];
        for(int n : nums){
            buckets[n] += n;
        }
        
        int[] dp = new int[10001];
        
        // base case 
        dp[0] = buckets[0];
        dp[1] = buckets[1];
        
        for(int i=2; i<10001; i++){
            // relation
            dp[i] = Math.max(dp[i-2] + buckets[i], dp[i-1]);
        }
        
        return dp[10000];
    }
    
    public int deleteAndEarn_usingMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + n);
            max = Math.max(max, n);
        }
        
        int[] dp = new int[max + 1];
        dp[0] = map.getOrDefault(0, 0);
        dp[1] = map.getOrDefault(1, 0); // since 1 is higher it should take 1 
        
        for(int i=2; i<= max; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + map.getOrDefault(i, 0));
        }
        
        return dp[max];
    }
    
}
