package interview.leetcode.prob;

/**
 * Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.


 * @author jojo
 * Jun 29, 2019 7:14:21 PM
 */
public class NumberOfLongestIncreasingSequence {
	public int findNumberOfLIS(int[] nums) {
        int n = nums.length, result = 0, max_len = 0;
        
        // len captures length of IS ending at i
        // cnt captures count of max IS ending at i
        int[] sequenceLength = new int[n], sequenceCount = new int[n];
        
        for(int i=0; i<n; i++){
            sequenceLength[i] = 1;
            sequenceCount[i] = 1;
            
            for(int j=0; j<i; j++){
                if(nums[i] > nums[j]){
                    // this implies if there is more than one IS from j to i
                    if(sequenceLength[i] == sequenceLength[j] + 1){
                        // need to account the previous computation of j
                        sequenceCount[i] += sequenceCount[j];
                    }
                    else if(sequenceLength[i] < sequenceLength[j] + 1){
                    	// previous length + 1
                    	sequenceLength[i] = sequenceLength[j] + 1; 
                        
                        // sequence count gets carry forward as 1,2 and 1,2,3 both should return 1
                        sequenceCount[i] = sequenceCount[j]; 
                    }
                }
            }
            
            if(max_len == sequenceLength[i]){
                result += sequenceCount[i];
            }
            else if(max_len < sequenceLength[i]){
                max_len = sequenceLength[i];
                result = sequenceCount[i];
            }
        }
        
        return result;
    }
}
