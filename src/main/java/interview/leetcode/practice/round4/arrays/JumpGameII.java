package interview.leetcode.practice.round4.arrays;

public class JumpGameII {
    public int jump(int[] nums) {
        if(nums.length < 2){
            return 0;
        }
        
        int cur = nums[0], max = nums[0], jumpCount = 1;
        
        for(int i=1; i<nums.length; i++){
            max--;
            cur--;
            max = Math.max(max, nums[i]);

            if(cur >= (nums.length - 1 - i)){
                break;
            }
            
            if(cur == 0){
                cur = max;
                jumpCount++;
            }
        }
        
        return jumpCount;
    }
}
