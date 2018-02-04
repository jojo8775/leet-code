package interview.leetcode.prob;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
 * @author jojo
 *
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k == 0 || nums.length == 0){
            return new int[]{};
        }
        
        Deque<Integer> dQueue = new LinkedList<>();
        k = Math.min(k, nums.length);
        
        for(int i=0; i<k-1; i++){
            if(dQueue.isEmpty()){
                dQueue.offerFirst(nums[i]);
            }
            else{
                add(dQueue, nums[i]);
            }
        }
        
        int[] result = new int[nums.length - (k-1)];
        
        for(int i=k-1, j=0; i<nums.length; i++, j++){
            add(dQueue, nums[i]);
            result[j] = dQueue.peekLast();
            
            if(dQueue.peekLast() == nums[j]){
                dQueue.pollLast();
            }
        }
        
        return result;
    }
    
    private void add(Deque<Integer> dQueue, int val){
        while(!dQueue.isEmpty() && dQueue.peekFirst() < val){
            dQueue.pollFirst();
        }
        
        dQueue.offerFirst(val);
    }
}
