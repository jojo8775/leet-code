package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 * @author jojo
 *
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique_ieterative(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        result.offer(new ArrayList<Integer>());
        
        for(int i=0; i<nums.length; i++){
            int size = result.size();
            for(;size>0;size--){
                List<Integer> top = result.pollFirst();
                for(int j=0; j<=top.size(); j++){
                    
                    // this is to get rid of duplicates
                    if(j>0 && top.get(j-1) == nums[i]){
                        break;
                    }
                    
                    List<Integer> l = new ArrayList<>(top);
                    l.add(j, nums[i]);
                    
                    result.offer(l);
                }
            }
        }
        
        return result;
    }
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return list;
    }
    
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<Integer>(tempList));
            return;
        }
        
        for(int i = 0; i < nums.length; i++){
            if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]){
                continue;  
            } 
            
            used[i] = true; 
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, used);
            used[i] = false; 
            tempList.remove(tempList.size() - 1);
        }
    }
    
    public static void main(String[] args){
    	int[] nums = {1,1,1,2};
    	List<List<Integer>> result = new PermutationsII().permuteUnique(nums);
    	
    	for(List<Integer> ll : result){
    		for(int i : ll){
    			System.out.print(i + ", ");
    		}
    		System.out.println();
    	}
    }
}
