package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(result, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        
        return result;
    }
    
    private void permute(List<List<Integer>> result, List<Integer> list, int[] nums, boolean[] visited){
        if(list.size() == nums.length){
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        for(int i=0; i<nums.length; i++){
            if(visited[i] || (i > 0 && nums[i-1] == nums[i] && !visited[i-1])){
                continue;
            }
            
            list.add(nums[i]);
            visited[i] = true;
            
            permute(result, list, nums, visited);
            
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
