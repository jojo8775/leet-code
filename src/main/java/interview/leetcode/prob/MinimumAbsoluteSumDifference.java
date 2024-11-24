package interview.leetcode.prob;

import java.util.TreeSet;

/**
 * You are given two positive integer arrays nums1 and nums2, both of length n.

The absolute sum difference of arrays nums1 and nums2 is defined as the sum of |nums1[i] - nums2[i]| for each 0 <= i < n (0-indexed).

You can replace at most one element of nums1 with any other element in nums1 to minimize the absolute sum difference.

Return the minimum absolute sum difference after replacing at most one element in the array nums1. Since the answer may be large, return it modulo 109 + 7.

|x| is defined as:

x if x >= 0, or
-x if x < 0.
 

Example 1:

Input: nums1 = [1,7,5], nums2 = [2,3,5]
Output: 3
Explanation: There are two possible optimal solutions:
- Replace the second element with the first: [1,7,5] => [1,1,5], or
- Replace the second element with the third: [1,7,5] => [1,5,5].
Both will yield an absolute sum difference of |1-2| + (|1-3| or |5-3|) + |5-5| = 3.
Example 2:

Input: nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
Output: 0
Explanation: nums1 is equal to nums2 so no replacement is needed. This will result in an 
absolute sum difference of 0.
Example 3:

Input: nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
Output: 20
Explanation: Replace the first element with the second: [1,10,4,4,2,7] => [10,10,4,4,2,7].
This yields an absolute sum difference of |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
 

Constraints:

n == nums1.length
n == nums2.length
1 <= n <= 105
1 <= nums1[i], nums2[i] <= 105
Accepted
26,866
Submissions
86,834
 * 
 * Nov 22, 2024 - 3:32:08 PM
 * Jojo 
 */
public class MinimumAbsoluteSumDifference {
	public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int mod = (int)(1e9 + 7);
            
        long totalDiff = 0;
        TreeSet<Integer> binarySearchTree = new TreeSet<Integer>();
        
        for(int i=0; i<nums1.length; ++i) {
            totalDiff = (totalDiff + Math.abs(nums1[i]-nums2[i]))%mod;
            binarySearchTree.add(nums1[i]);
        }

        long decrement = 0;

        for(int i=0; i<nums1.length; ++i) {
            long original = Math.abs(nums1[i]-nums2[i]);

            if(decrement < original) {
                if(binarySearchTree.floor(nums2[i]) != null) {
                    // removing the new diff from the original and then taking the max out of it. 
                    // if the replacement turnsout to be worse the result will be -ve. We will never 
                    // consider that value because decremenet starts at 0
                    decrement = Math.max(decrement, original - Math.abs(binarySearchTree.floor(nums2[i]) - nums2[i]));
                }
                if(binarySearchTree.ceiling(nums2[i]) != null) {
                    decrement = Math.max(decrement, original - Math.abs(binarySearchTree.ceiling(nums2[i]) - nums2[i]));
                }  
            }
        }
        
        //System.out.println("t: " + totalDiff + "   dec: " + decrement);
        
        // adding mod because the totalDiff can be smaller than the diff because of the mod. To make the 
        // adjustment adding mod. 
        long result = totalDiff - decrement + mod;
        result %= mod;
        return (int)(result);
    }
}
