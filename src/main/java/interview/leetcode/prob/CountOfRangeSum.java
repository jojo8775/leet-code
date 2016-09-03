package interview.leetcode.prob;

import java.util.Map.Entry;
import java.util.TreeMap;

public class CountOfRangeSum {
	public int countRangeSum(int[] nums, int lower, int upper) {

		int[] sum = new int[nums.length + 1];
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		map.put(0, 1);

		// constructing map and creating all sub sets
		for (int i = 0; i < nums.length; i++) {
			sum[i + 1] = sum[i] + nums[i];
			map.put(sum[i + 1], map.containsKey(sum[i + 1]) ? map.get(sum[i + 1]) + 1 : 1);
		}

		int count = 0;

		// checking subset
		for (int i = 0; i < nums.length; i++) {
			map.put(sum[i], map.get(sum[i]) - 1);
			if (map.get(sum[i]) == 0) {
				map.remove(sum[i]);
			}

			for (Entry<Integer, Integer> entry : map.subMap(sum[i] + lower, sum[i] + upper + 1).entrySet()) {
				count += entry.getValue();
			}
		}
		
		return count;
	}
	
	public static void main(String[] args){
//		int[] arr = {-2,5,3,-4,-1};
//		System.out.println(new CountOfRangeSum().countRangeSum(arr, -2, 2));
		
		System.out.println(new CountOfRangeSum().countRangeSum(new int[] {8}, 0, 8));
	}
}
