package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of integers and an integer k, find out whether there are two
 * distinct indices i and j in the array such that nums[i] = nums[j] and the
 * difference between i and j is at most k.
 * 
 * @author jojo
 *
 */
public class ContainsDuplicateII {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        // sliding window
        for(int i=0; i<nums.length; i++){
            if(set.contains(nums[i])){
                return true;
            }

            set.add(nums[i]);

            if(i >= k){
                set.remove(nums[i - k]);
            }
        }

        return false;
    }
	
	public boolean containsNearbyDuplicate_1(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				if (i - map.get(nums[i]) <= k) {
					return true;
				}
			}

			map.put(nums[i], i);
		}

		return false;
	}
}
