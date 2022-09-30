package interview.leetcode.prob;

/**
 * Note: This is an extension of House Robber.
 * 
 * After robbing those houses on that street, the thief has found himself a new
 * place for his thievery so that he will not get too much attention. This time,
 * all houses at this place are arranged in a circle. That means the first house
 * is the neighbor of the last one. Meanwhile, the security system for these
 * houses remain the same as for those in the previous street.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.
 * 
 * Credits: Special thanks to @Freezen for adding this problem and creating all
 * test cases.
 * 
 * 
 * @author jojo
 *
 */
public class HouseRobberII {
	public int rob_easy_toUnderstand(int[] nums) {
        int len = nums.length;
        
        if(len == 1){
            return nums[0];
        }   
        
        if(len == 2){
            return Math.max(nums[0], nums[1]);
        }
        
        // since this is a circular array, including first will excluse the last 
        int includingFirst = findMax(nums, 0, len - 2);
        
        // if we include the last then first needs to be excluded
        int excludingFirst = findMax(nums, 1, len - 1);
        
        return Math.max(includingFirst, excludingFirst);
    }
    
    // this is same as House Robber 1 
    private int findMax(int[] arr, int beg, int end){
        int len = end - beg + 1;
        int[] dp = new int[len];
        
        for(int i=0; i<len; i++){
            if(i == 0){
                dp[0] = arr[beg];
            }
            else if(i == 1){
                dp[1] = Math.max(arr[beg], arr[beg + 1]);
            }
            else{
                dp[i] = Math.max(dp[i-1], dp[i-2] + arr[beg + i]);
            }
        }
        
        return dp[len - 1];
    }
	
	
	public int rob(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		if (nums.length == 1) {
			return nums[0];
		}

		int firstInc = nums[0]; // includes first and current
		int firstExc = 0; // includes first and excludes current
		int nonFirstInc = 0; // excludes first andbut includes curent
		int nonFirstExc = 0; // excludes first and current

		for (int i = 1; i < nums.length; i++) {
			int prev = firstInc;
			firstInc = firstExc + nums[i];
			firstExc = Math.max(prev, firstExc);

			prev = nonFirstInc;
			nonFirstInc = nonFirstExc + nums[i];
			nonFirstExc = Math.max(prev, nonFirstExc);
		}

		// minimum by either taking first or by not taking it
		int maxInc = Math.min(firstInc, nonFirstInc);

		return Math.max(maxInc, firstExc);
	}
}
