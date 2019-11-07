package interview.leetcode.practice.round4.stack;

import java.util.ArrayList;
import java.util.List;

public class SubSetII {
	public List<List<Integer>> findSubsets(int[] nums){
	    List<List<Integer>> result = new ArrayList<>();

	    result.add(new ArrayList<Integer>());
	    
	    int beg = 0;
	    for(int i=0; i<nums.length; i++){
	        if(i>0 && nums[i] != nums[i-1]){
	            beg = 0;
	        }

	        int end = result.size();

	        while(beg < end){
	            List<Integer> temp = new ArrayList<>(result.get(beg++));
	            temp.add(nums[i]);

	            result.add(temp);
	        }
	    }

	    return result;
	}

}
