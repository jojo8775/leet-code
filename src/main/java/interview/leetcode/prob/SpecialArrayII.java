package interview.leetcode.prob;

import java.util.TreeSet;

/**
 * An array is considered special if every pair of its adjacent elements contains two numbers with different parity.

You are given an array of integer nums and a 2D integer matrix queries, where for queries[i] = [fromi, toi] your task is to check that subarray nums[fromi..toi] is special or not.

Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.

 

Example 1:

Input: nums = [3,4,1,2,6], queries = [[0,4]]

Output: [false]

Explanation:

The subarray is [3,4,1,2,6]. 2 and 6 are both even.

Example 2:

Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]

Output: [false,true]

Explanation:

The subarray is [4,3,1]. 3 and 1 are both odd. So the answer to this query is false.
The subarray is [1,6]. There is only one pair: (1,6) and it contains numbers with different parity. So the answer to this query is true.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= queries.length <= 105
queries[i].length == 2
0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
Accepted
40,812
Submissions
121,809
 * 
 * Dec 8, 2024 - 7:41:59 PM
 * Jojo 
 */
public class SpecialArrayII {
	public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        TreeSet<Integer> splitStart = new TreeSet<>();
        
        int start = 0;
        for(int i=0; i<nums.length-1; i++){
            if((nums[i] % 2 == 0 && nums[i + 1] % 2 == 0) || (nums[i] % 2 == 1 && nums[i+1] % 2 == 1)){
                splitStart.add(start);
                start = i + 1;
            }
        }
        
        splitStart.add(start);
        
        boolean[] result = new boolean[queries.length];
        
        for(int i=0; i<queries.length; i++){
            int[] q = queries[i];
            
            int idx1 = splitStart.floor(q[0]);
            int idx2 = splitStart.floor(q[1]);
            
            if(idx1 == idx2){
                result[i] = true;
            }
            else{
                result[i] = false;
            }
        }
        
        return result;
    }
}
