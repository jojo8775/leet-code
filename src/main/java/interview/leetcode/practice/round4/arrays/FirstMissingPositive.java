package interview.leetcode.practice.round4.arrays;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        for(int i=0; i<nums.length; i++){
            if(nums[i] - 1 == i || nums[i] < 1 || nums[i] >= nums.length){
                continue;
            }
            
            while(nums[i] - 1 != i){
                if(nums[i] >= nums.length || nums[i] < 1 || nums[i] == nums[nums[i] - 1]){
                    break;
                }
                
                int temp = nums[i];
                nums[i] = nums[temp -1];
                nums[temp - 1] = temp;
            }
        }
        
        int result = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] != result){
                break;
            }
            
            result++;
        }
        
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new FirstMissingPositive().firstMissingPositive(new int[] { 1, 2, 2, 1, 3, 1, 0, 4, 0 }));
    }
}
