package interview.leetcode.prob;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

Subscribe to see which companies asked this question
 * @author jojo
 *
 */
public class LongesstConsecutiveSequence {
	private LinkedList<Integer> result = new LinkedList<Integer>();
	
	public int longestConsecutive(int[] nums) {
        int maxLen = 0;
        
        // storing all in a set
        Set<Integer> set = new HashSet<Integer>();
        for(int i : nums){
            set.add(i);
        }
        
        for(int i=0; i<nums.length; i++){
            int left = nums[i], right=nums[i]+1, count = 0;
            LinkedList<Integer> list = new LinkedList<Integer>();
            
            //removing left
            while(set.contains(left)){
                count++;
                list.addFirst(left);
                set.remove(left--);
            }
            
            //removing right
            while(set.contains(right)){
                count++;
                list.add(right);
                set.remove(right++);
            }
            
            //checking the max length
            maxLen = Math.max(maxLen, count);
            
            if(result.size() < list.size()){
            	result = list;
            }
        }
        
        return maxLen;
    }
	
	public static void main(String[] args){
		int[] arr = {100, 4, 200, 1, 3, 2};
		LongesstConsecutiveSequence l = new LongesstConsecutiveSequence();
		System.out.println(l.longestConsecutive(arr));
		
		for(int i : l.result){
			System.out.print(i + ", ");
		}
	}
}
