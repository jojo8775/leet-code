package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.

 

Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1
Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109
Accepted
41,457
Submissions
124,325
 * @author jojo
 * Dec 20, 2021 1:57:49 PM
 */
public class MinimumOperationsToReduceXToZero {
	public int minOperations(int[] nums, int x) {
        int total = Arrays.stream(nums).sum();
        
        int len = nums.length, cur = 0, maxi = -1;
        
        for(int left = 0, right = 0; right < len; right++){
            cur += nums[right];
            
            while(cur > (total - x) && left <= right){
                cur -= nums[left++];
            }
            
            if(cur == (total - x)){
                maxi = Math.max(maxi, right - left + 1);
            }
        }
        
        return maxi == -1 ? maxi : len - maxi;
	}
	
    public int minOperations_1(int[] nums, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, nums.length -1, x});
        
        Set<String> visited = new HashSet<>();
        int count = 0;
        
        while(!queue.isEmpty()){
            int len = queue.size();
            count++;
            
            for(int i=0; i<len; i++){
                int[] top = queue.poll();
                
                if(top[2] - nums[top[0]] == 0 || top[2] - nums[top[1]] == 0){
                    return count;
                }
                
                if(top[0] < top[1]){
                    if(visited.add((top[0] + 1) + "-" + top[1])){
                    	System.out.println("l:" + (top[0] + 1) + "  r:" + top[1] + "  v:" + (top[2] - nums[top[0]]));
                        queue.offer(new int[]{top[0] + 1, top[1], top[2] - nums[top[0]]});
                    }
                    
                    if(visited.add(top[0] + "-" + (top[1] - 1))){
                    	System.out.println("l:" + top[0] + "  r:" + (top[1] - 1) + "  v:" + (top[2] - nums[top[1]]));
                        queue.offer(new int[]{top[0], top[1] - 1, top[2] - nums[top[1]]});
                    }
                }
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
    	var result = new MinimumOperationsToReduceXToZero().minOperations(new int[] {3,2,20,1,1,3}, 10);
    	System.out.println(result);
    }
}
