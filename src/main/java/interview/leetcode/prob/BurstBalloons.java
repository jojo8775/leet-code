package interview.leetcode.prob;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * @author jojo
 *
 */
public class BurstBalloons {
	public int maxCoins(int[] nums) {
        int[] iNums = new int[nums.length + 2];
        int length = 1;
        
        //ignoring any 0 val
        for(int i : nums){
            if(i > 0){
                iNums[length++] = i;
            }
        }
        
        //marking first and last as 1 as per the problem. They are dummy and dont add to the result
        iNums[0] = 1;
        iNums[length++] = 1;
        
        // two dimentional array to store all the possible sub array sum
        int[][] dp = new int[length][length];
        
        for(int k=2; k<length; k++){
            for(int left = 0; left<length - k; left++){
                int right = left + k;
                for(int i = left + 1; i<right; i++){
                    //since after a ballon burst left and right becomes adjacent
                    dp[left][right] = Math.max(dp[left][right], iNums[left] * iNums[i] * iNums[right] + dp[left][i] + dp[i][right]);
                }
            }
        }
        
        return dp[0][length - 1];
    }
	
	public static void main(String[] args){
		int[] arr = {3,1,5,8};
		System.out.println(new BurstBalloons().maxCoins(arr));
	}
}
