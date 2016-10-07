package interview.leetcode.prob.paid;

import java.util.Stack;

public class WiggleSort {
	public void wiggleSort(int[] nums) {
//		dfs(nums, true, 0);
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(nums[0]);
		
		while(!stack.isEmpty()){
			int top = stack.pop();
			if(stack.isEmpty()){
				if(top + 1 < nums.length){
					swap(nums, top < 0 ? 0 : top, top + 1);
					stack.push(top + 1);
				}
			}
			else if(){
				
			}
		}
	}

	private boolean dfs(int[] nums, boolean low, int idx) {
		if (idx == nums.length) {
			return true;
		}

		for (int i = idx; i < nums.length; i++) {
			if (idx == 0) {
				swap(nums, idx, i);
				if (dfs(nums, !low, idx + 1)) {
					return true;
				}
			} else if (low == true && nums[i] <= nums[idx - 1]) {
				swap(nums, idx, i);
				if (dfs(nums, !low, idx + 1)) {
					return true;
				}
			} else if (low == false && nums[i] >= nums[idx - 1]) {
				swap(nums, idx, i);
				if (dfs(nums, !low, idx + 1)) {
					return true;
				}
			}
		}

		return false;
	}

	private void swap(int[] nums, int idx1, int idx2) {
		int temp = nums[idx1];
		nums[idx1] = nums[idx2];
		nums[idx2] = temp;
	}
	
	public static void main(String[] args){
		new WiggleSort().wiggleSort(new int[] {3, 5, 2, 1, 6, 4});
	}
}
