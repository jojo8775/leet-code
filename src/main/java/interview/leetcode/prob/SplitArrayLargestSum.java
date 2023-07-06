package interview.leetcode.prob;

/**
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
Given m satisfies the following constraint: 1 ≤ m ≤ length(nums) ≤ 14,000.

Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 * @author jojo
 *
 */
public class SplitArrayLargestSum {
	public int splitArray(int[] nums, int m) {
        int maxEntry = 0;
        long totalSum = 0;
        
        for(int n : nums){
            // finding the highest entry as this will be the minimum total of at least one sub array.
            maxEntry = Math.max(maxEntry, n);
            
            // finding total so that we can guage the target sum for each sub array
            totalSum += n;
        }
        
        // max entry becomes the reference of predicated lowest sum.
        long low = maxEntry, high = totalSum;
        
        while(low<high){
            // mid represents the predicted optimal sum for each sub array
            long mid = low + (high - low)/2;
            
            if(!isValid(nums, mid, m)){
                // if mid can be split among more than m sub array then we need to aim for higher predicted sum
                low = mid + 1;
            }
            else{
                // if mid cannot be split among m sub array then we need to aim for lower predicted sum
                high = mid;
            }
        }
        
        return (int) low;
    }
    
    private boolean isValid(int[] nums, long target, int numberOfSplit){
        long currentSum = 0;
        int splitCount = 1;
        
        for(int n : nums){
            currentSum += n;
            
            // target serves as the predicted sum for each sub array
            if(currentSum > target){
                // since current sum exceeded target so we need to put rest of element in different sub array
                currentSum = n;
                splitCount ++;
            }
            
            // if predicted sum can accommodate more than m sub array then its needs to be increased.
            if(splitCount > numberOfSplit){
                return false;
            }
        }
        
        return true;
    }
}
