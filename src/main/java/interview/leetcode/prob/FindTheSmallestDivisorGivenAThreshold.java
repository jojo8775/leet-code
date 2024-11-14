package interview.leetcode.prob;

/**
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.

Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).

The test cases are generated so that there will be an answer.

 

Example 1:

Input: nums = [1,2,5,9], threshold = 6
Output: 5
Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1. 
If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2). 
Example 2:

Input: nums = [44,22,33,11,1], threshold = 5
Output: 44
 

Constraints:

1 <= nums.length <= 5 * 104
1 <= nums[i] <= 106
nums.length <= threshold <= 106
Accepted
237,132
Submissions
384,057
 * 
 * 
 * Nov 13, 2024 - 11:59:06 PM
 * Jojo 
 */
public class FindTheSmallestDivisorGivenAThreshold {
	public int smallestDivisor(int[] nums, int threshold) {
        int beg = 1, end = nums[0];
        
        for(int n : nums){
            end = Math.max(end, n);
        }
       
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            int sum = findSum(mid, nums, threshold);
            
            if(sum <= threshold){
                end = mid - 1;
            }
            else{
                beg = mid + 1;
            }
        }
               
        return beg;        
    }
    
    private int findSum(int divisor, int[] arr, int target){
        int sum = 0;
        
        for(int i=0; i<arr.length; i++){
            sum += (arr[i] + divisor - 1)/divisor;
            
            if(sum > target){
                break;
            }
        }
        
        return sum;
    }
}
