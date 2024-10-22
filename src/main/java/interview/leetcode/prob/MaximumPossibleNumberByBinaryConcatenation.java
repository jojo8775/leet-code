package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given an array of integers nums of size 3.

Return the maximum possible number whose binary representation can be formed by concatenating the binary representation of all elements in nums in some order.

Note that the binary representation of any number does not contain leading zeros.

 

Example 1:

Input: nums = [1,2,3]

Output: 30

Explanation:

Concatenate the numbers in the order [3, 1, 2] to get the result "11110", which is the binary representation of 30.

Example 2:

Input: nums = [2,8,16]

Output: 1296

Explanation:

Concatenate the numbers in the order [2, 8, 16] to get the result "10100010000", which is the binary representation of 1296.

 

Constraints:

nums.length == 3
1 <= nums[i] <= 127
Accepted
25,020
Submissions
38,815
 * 
 * Oct 6, 2024 - 5:02:15 PM
 * Jojo 
 */
public class MaximumPossibleNumberByBinaryConcatenation {
	public int maxGoodNumber(int[] nums) {
        List<Integer> ll = new ArrayList<>();
        for(int n : nums){
            ll.add(n);
        }
        
        Collections.sort(ll, (a,b) ->{
            String s1 = Integer.toBinaryString(a);
            String s2 = Integer.toBinaryString(b);
            
            String ss1 = s1 + s2;
            String ss2 = s2 + s1;
            
            return ss2.compareTo(ss1);
        });
        
        StringBuilder sb = new StringBuilder();
        for(int n : ll){
            
            //System.out.println("n:" + n + "  bs:" + Integer.toBinaryString(n));
            
            sb.append(Integer.toBinaryString(n));
        }
        
        return Integer.parseInt(sb.toString(), 2);
    }
}
