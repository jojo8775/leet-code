package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * You are given an array nums consisting of positive integers.

Starting with score = 0, apply the following algorithm:

Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
Add the value of the chosen integer to score.
Mark the chosen element and its two adjacent elements if they exist.
Repeat until all the array elements are marked.
Return the score you get after applying the above algorithm.

 

Example 1:

Input: nums = [2,1,3,4,5,2]
Output: 7
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
- 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
- 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
Our score is 1 + 2 + 4 = 7.
Example 2:

Input: nums = [2,3,5,1,3,2]
Output: 5
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
- 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
Our score is 1 + 2 + 2 = 5.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 106
Accepted
31,434
Submissions
54,152
 * 
 * Dec 12, 2024 - 7:29:12 PM
 * Jojo 
 */
public class FindScoreOfAnArrayAfterMarkingAllElements {
	public long findScore(int[] nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        for(int i=0; i<nums.length; i++){
            pq.offer(new int[]{nums[i], i});
        }
        
        long score = 0;
        
        while(!pq.isEmpty()){
            int[] top = pq.poll();
            int idx = top[1], val = top[0];
            
            if(nums[idx] == -1){
                continue;
            }
            
            score += (long)val;
            nums[idx] = -1;
            
            if(idx > 0){
                nums[idx - 1] = -1;
            }
            
            if(idx < nums.length - 1){
                nums[idx + 1] = -1;
            }
        }
        
        return score;
    }
}
