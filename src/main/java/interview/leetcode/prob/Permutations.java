package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 * @author jojo
 *
 */
public class Permutations {
	public List<List<Integer>> permute(int[] nums) {
		LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
		res.add(new ArrayList<Integer>());
		for (int n : nums) {
			int size = res.size();
			for (; size > 0; size--) {
				List<Integer> r = res.pollFirst();
				for (int i = 0; i <=r.size(); i++) {
					List<Integer> t = new ArrayList<Integer>(r);
					t.add(i, n);
					res.add(t);
				}
			}
		}
		
		return res;
	}
	
	public List<List<Integer>> permute_dfs(int[] nums){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		dfs(result, nums, 0, nums.length -1);
		
		return result;
	}
	
	private void dfs(List<List<Integer>> result, int[] nums, int beg, int end){
		if(beg == end){
			List<Integer> list = new ArrayList<Integer>();
			for(int i=0; i<nums.length; i++){
				list.add(nums[i]);
			}
			
			result.add(list);
			return;
		}
		
		for(int i=beg; i<=end; i++){
			swap(nums, i, beg);
			dfs(result, nums, beg + 1, end);
			swap(nums, beg, i);
		}
	}
	
	private void swap(int[] nums, int beg, int end){
		if(beg == end){
			return;
		}
		
		int temp = nums[beg];
		nums[beg] = nums[end];
		nums[end] = temp;
	}

	public static void main(String[] args) {
		int[] nums = { 1,2, 3};
		List<List<Integer>> result = new Permutations().permute_dfs(nums);

		for (List<Integer> list : result) {
			for (int i : list) {
				System.out.print(i + ", ");
			}
			System.out.println();
		}
	}
}
