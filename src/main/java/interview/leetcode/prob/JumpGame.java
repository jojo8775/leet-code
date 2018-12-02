package interview.leetcode.prob;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
 * @author jojo
 *
 */
public class JumpGame {
	public boolean canJump(int[] nums) {
        if(nums==null || nums.length < 2){
            return true;
        }
        
        int bal = nums[0], curBal = bal;
        
        //taking greedy approach
        for(int i=1; i<=bal; i++){
            //have enought jumps to reach
            if(bal >= nums.length - 1){
                return true;
            }
            
            //taking the maximum combination possible in a greedy approach
            curBal = Math.max(curBal, i+nums[i]);
            
            if(i == bal){
                // not enought steps to reach end
                if(curBal <= bal){
                    return false;
                }
                //take the current maximum combination and proceed
                else{
                    bal = curBal;
                }
            }
        }            
        
        return false;
    }
	
	/**
	 * This is an advanced approach
	 */
	public boolean canJum_adv(int[] nums) {
		// input is too short and needs no jump 
		if(nums.length < 2){
            return true;
        }
        
		// start from the end. If end can be reached from given position then end becomes the current position. 
        int limit = nums.length - 1;
        for(int i=limit; i>=0; i--){
            if(i + nums[i] >= limit){
                limit = i;
            }
        }
        
        return limit == 0;
    }
}
