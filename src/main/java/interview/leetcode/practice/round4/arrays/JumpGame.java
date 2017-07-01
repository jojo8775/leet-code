package interview.leetcode.practice.round4.arrays;

public class JumpGame {
    public boolean canJump(int[] nums) {
        if(nums.length < 2){
            return true;
        }
        
        int curJump = nums[0], maxJump = nums[0];
        
        int idx = 1;
        while(idx < nums.length && curJump > 0){
            curJump--;
            if(curJump >= (nums.length - idx - 1)){
                return true;
            }
            
            maxJump--;
            maxJump = Math.max(nums[idx++], maxJump);

            if(curJump == 0){
                curJump = maxJump;
            }
        }
        
        return false;
    }
}
