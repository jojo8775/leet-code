package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {
	public int lengthOfLIS(int[] nums) {
        if(nums.length < 2){
            return nums.length;
        }
        
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        
        for(int i=1; i<nums.length; i++){
            int pos = findPos(list, nums[i]);
            
            // System.out.println(pos);
            
            if(pos == list.size()){
                list.add(nums[i]);
            }
            else{
                list.set(pos, nums[i]);
            }
        }
        
        return list.size();
    }
    
    private int findPos(List<Integer> list, int val){
        int beg = 0, end = list.size() - 1;
        
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            if(list.get(mid) == val){
                return mid;
            }
            
            if(list.get(mid) < val){
                beg = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        
        return beg;
    }
    
    public static void main(String[] args) {
    	int result = new LongestIncreasingSubsequence().lengthOfLIS(new int[] {10,9,2,4,7,5,-1});
    	System.out.println(result);
    }
}
