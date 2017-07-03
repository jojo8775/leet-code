package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

 * @author jojo
 *Jul 3, 20171:25:43 AM
 */
public class ValidTriangleNumber {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        
        // sum of two smaller side should be greater than the third side
        int count = 0;
        for(int i=2; i<nums.length; i++){
            int j = 0, k = i-1;
            while(j < k){
                // if this is true, it will be true for all j to k combinations
                if((nums[j] + nums[k]) > nums[i]){
                    count += (k - j);
                    // finding new k
                    k--;
                }
                else{
                    // finding new j
                    j++;
                }
            }
        }
        
        return count;
    }
}
