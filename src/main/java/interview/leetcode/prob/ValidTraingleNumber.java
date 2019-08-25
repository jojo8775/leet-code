package interview.leetcode.prob;

import java.util.Arrays;

/**
 *Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
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
 * Aug 24, 2019 4:27:18 PM
 */
public class ValidTraingleNumber {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        
        
        // the formula is a + b > c, left and right corresponds to left and right where as c corresponds to i
        for(int i=nums.length - 1; i>= 2; i--){
            int left = 0, right = i - 1;
            
            while(left < right){
                if(nums[left] + nums[right] > nums[i]){
                    count += (right - left);
                    right--;
                }
                else{
                    left++;
                }
            }
        }
        
        return count;
    }
}
