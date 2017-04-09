package interview.leetcode.practice.round2;

public class JumpII {
    public int jump(int[] nums) {
        if(nums.length <= 1){
            return 0;
        }
        
        int current = nums[0], reserve = 0, idx = 0, jumps = 1;
        
        while(current > 0 && idx < nums.length){
            idx++;
            current --;
            
            if(nums.length - (idx + 1) <= current){
                break;
            }
            
            reserve --;
            
            reserve = Math.max(reserve, nums[idx]);
            
            if(current == 0){
                current = reserve;
                reserve = 0;
                jumps++;
            }
        }
        
        return jumps;
    }
}
