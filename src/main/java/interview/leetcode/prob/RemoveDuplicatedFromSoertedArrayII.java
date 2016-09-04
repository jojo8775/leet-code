package interview.leetcode.prob;

/**
 * Follow up for "Remove Duplicates": What if duplicates are allowed at most
 * twice?
 * 
 * For example, Given sorted array nums = [1,1,1,2,2,3],
 * 
 * Your function should return length = 5, with the first five elements of nums
 * being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new
 * length.
 * 
 * @author jojo
 *
 */
public class RemoveDuplicatedFromSoertedArrayII {
	public int removeDuplicates(int[] nums) {
		int count = 0, prev = 0;

		for (int i = 1; i < nums.length; i++) {
			if (nums[prev] == nums[i] && count < 1) {
				count++;
				nums[++prev] = nums[i];
			} else if (nums[prev] != nums[i]) {
				count = 0;
				nums[++prev] = nums[i];
			}
		}

		return ++prev;
	}

	//taking bottom up dynamic programming approach
	    public int maxCoins(int[] nums) {
	        //base case
	        if(nums.length == 0){
	            return 0;
	        }
	        
	        int[][] dp = new int[nums.length][nums.length];
	
			// Considering only one element in the sub array
			for (int i = 0; i < nums.length; i++) {
				int left = i == 0 ? 1 : nums[i - 1];
				int right = i == nums.length - 1 ? 1 : nums[i + 1];
				dp[i][i] = left * nums[i] * right;
			}
	
			// K represents the length of sub array
			for (int k = 1; k < nums.length; k++) {
				for (int i = 0; i < nums.length - k; i++) {
					for (int j = i; j <= i + k; j++) {
						// value of the left sub array
						int leftSubArray = j == 0 ? 0 : dp[i][j - 1];
						// value of the right sub array
						int rightSubArray = j == nums.length - 1 ? 0 : dp[j + 1][i + k];
	
						// next available left value
						int leftVal = i == 0 ? 1 : nums[i - 1];
						// next available right value
						int rightVal = i + k + 1 == nums.length ? 1 : nums[i + k + 1];
	
						// maximum of all the possible combinations
						// The idea is to find the last balloon to burst for each
						// sub array to obtain the maximum result
						dp[i][i + k] = Math.max(dp[i][i + k],
								leftSubArray + rightSubArray + (leftVal * nums[j] * rightVal));
					}
				}
			}
	
			return dp[0][nums.length - 1];
	    }
}
