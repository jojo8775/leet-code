package interview.leetcode.prob;

/**
 * You are given an array of integers nums of length n and a positive integer k.

The power of an array is defined as:

Its maximum element if all of its elements are consecutive and sorted in ascending order.
-1 otherwise.
You need to find the power of all subarrays of nums of size k.

Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].

 

Example 1:

Input: nums = [1,2,3,4,3,2,5], k = 3

Output: [3,4,-1,-1,-1]

Explanation:

There are 5 subarrays of nums of size 3:

[1, 2, 3] with the maximum element 3.
[2, 3, 4] with the maximum element 4.
[3, 4, 3] whose elements are not consecutive.
[4, 3, 2] whose elements are not sorted.
[3, 2, 5] whose elements are not consecutive.
Example 2:

Input: nums = [2,2,2,2,2], k = 4

Output: [-1,-1]

Example 3:

Input: nums = [3,2,3,2,3,2], k = 2

Output: [-1,3,-1,3,-1]

 

Constraints:

1 <= n == nums.length <= 500
1 <= nums[i] <= 105
1 <= k <= n
Accepted
61,300
Submissions
105,622
 * 
 * 
 * Nov 15, 2024 - 11:05:36 PM
 * Jojo 
 */
public class FindThePowerOfKActiveI {
	public int[] resultsArray(int[] nums, int k) {
        
        if(k == 1){
            return nums;
        }
        
        int start = 0, cur = 0;
        for(int i=1; i<k-1; i++){
            if(nums[i-1] + 1 != nums[i]){
                start = i;
            }
        }
        
        int[] result = new int[nums.length - k + 1];
        
        for(int i=k-1, j=0; i<nums.length; i++, j++){
            if(nums[i - 1] + 1 != nums[i]){
                start = i;
            }
            
            if(i - start == k - 1){
                result[j] = nums[start] + k - 1;
            }
            else{
                result[j] = -1;
            }
            
            start = Math.max(start, j+1);
        }
        
        return result;
    }
}
