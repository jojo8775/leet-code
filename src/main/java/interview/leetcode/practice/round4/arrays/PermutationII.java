package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        permute(result, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return result;
    }
    
    private void permute(List<List<Integer>> result, List<Integer> list, int[] nums, boolean[] visited){
        if(list.size() == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }
        
        for(int i=0; i<nums.length; i++){
            if(visited[i] || (i>0 && nums[i] == nums[i-1] && !visited[i-1])){
                continue;
            }
            
            visited[i] = true;
            list.add(nums[i]);
            permute(result, list, nums, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
