package interview.leetcode.prob.paid;

import java.util.List;
import java.util.PriorityQueue;

/**
 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

 

Example 1:

Input: [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
 

Note:

The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
 * @author jojo
 * Sep 28, 2019 1:37:04 AM
 */
public class SmallestRangeCoveringElementsFromKList {
	public int[] smallestRange(List<List<Integer>> nums) {
        // approach is similar to the merge K list problem. 
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
        
        // setting range to max_value and max to min_value
        int max = Integer.MIN_VALUE, range = Integer.MAX_VALUE, start = -1, end = -1;
        for(int i=0; i<nums.size(); i++){
            pq.offer(new Node(i, 0, nums.get(i).get(0)));
            max = Math.max(max, nums.get(i).get(0));
        }
        
        while(pq.size() == nums.size()){
            Node top = pq.poll();
            
            // storing the min range
            if(max - top.val < range){
                start = top.val;
                end = max;
                range = max - top.val;
            }
            
            // if a list is exhausted
            if(top.idx2 == nums.get(top.idx1).size() - 1){
                continue;
            }
            
            top.idx2 += 1;
            top.val = nums.get(top.idx1).get(top.idx2);
            
            if(max < top.val){
                max = top.val;
            }
            
            pq.offer(top);
        }
        
        return new int[]{start, end};
    }
    
    private static class Node{
        int idx1, idx2, val;
        
        public Node(int idx1, int idx2, int val){
            this.idx1 = idx1; 
            this.idx2 = idx2;
            this.val = val;
        }
    }
}
