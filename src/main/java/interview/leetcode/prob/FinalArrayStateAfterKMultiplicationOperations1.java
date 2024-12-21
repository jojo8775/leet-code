package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * You are given an integer array nums, an integer k, and an integer multiplier.

You need to perform k operations on nums. In each operation:

Find the minimum value x in nums. If there are multiple occurrences of the minimum value, select the one that appears first.
Replace the selected minimum value x with x * multiplier.
Return an integer array denoting the final state of nums after performing all k operations.

 

Example 1:

Input: nums = [2,1,3,5,6], k = 5, multiplier = 2

Output: [8,4,6,5,6]

Explanation:

Operation	Result
After operation 1	[2, 2, 3, 5, 6]
After operation 2	[4, 2, 3, 5, 6]
After operation 3	[4, 4, 3, 5, 6]
After operation 4	[4, 4, 6, 5, 6]
After operation 5	[8, 4, 6, 5, 6]
Example 2:

Input: nums = [1,2], k = 3, multiplier = 4

Output: [16,8]

Explanation:

Operation	Result
After operation 1	[4, 2]
After operation 2	[4, 8]
After operation 3	[16, 8]
 

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
1 <= k <= 10
1 <= multiplier <= 5
Accepted
85,405
Submissions
99,975
 * 
 * Dec 15, 2024 - 10:56:51 PM
 * Jojo 
 */
public class FinalArrayStateAfterKMultiplicationOperations1 {
	public int[] getFinalState(int[] nums, int k, int multiplier) {
        int len = nums.length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        for(int i=0; i<len; i++){
            pq.offer(new int[]{nums[i], i});
        }
        
        while(k > 0){
            k--;
            
            int[] top = pq.poll();
            
            top[0] *= multiplier;
            
            pq.offer(top);
        }
        
        while(!pq.isEmpty()){
            int[] top = pq.poll();
            nums[top[1]] = top[0];
        }
        
        return nums;
    }
}
