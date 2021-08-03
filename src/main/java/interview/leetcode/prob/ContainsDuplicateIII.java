package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i
 * and j in the array such that the difference between nums[i] and nums[j] is at
 * most t and the difference between i and j is at most k.
 * 
 * @author jojo
 *
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate_adv(int[] nums, int k, int t) {
        if(t < 0){
            return false;
        }
        
        Map<Long, Long> bucketMap = new HashMap<>();
        long n = t + 1;
        
        for(int i=0; i<nums.length; i++){
            long bucketId = findBucket(nums[i], n);
            
            // means there are two element which are withn t difference
            if(bucketMap.containsKey(bucketId)){
                return true;
            }
            
            // checking the left bucket
            if(bucketMap.containsKey(bucketId - 1) && Math.abs(bucketMap.get(bucketId - 1) - nums[i]) < n){
                return true;
            }
            
            // checking the right bucket
            if(bucketMap.containsKey(bucketId + 1) && Math.abs(bucketMap.get(bucketId + 1) - nums[i]) < n){
                return true;
            }
            
            bucketMap.put(bucketId, Long.valueOf(nums[i]));
            
            // maintaing the k window. >= because i is 0 index
            if(i >= k){
                bucketMap.remove(findBucket(nums[i - k], n));
            }
        }
        
        return false;
    }
    
    private long findBucket(long  x, long n){
        
        // +1 is added so that -3/5 should be -1 instead of 0
        return x < 0 ? (x + 1) / n - 1 : x/n;
    }
	
	
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (nums == null || nums.length == 0 || k <= 0) {
			return false;
		}

		TreeSet<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			Integer floor = set.floor(nums[i] + t);
			Integer ceiling = set.ceiling(nums[i] - t);

			if ((floor != null && floor >= nums[i]) || (ceiling != null && ceiling <= nums[i])) {
				return true;
			}

			set.add(nums[i]);

			if (i >= k) {
				set.remove(nums[i - k]);
			}
		}

		return false;
	}
	
	public static void main(String[] args) {
		int[] arr = { -1, -1 };
		System.out.println(new ContainsDuplicateIII().containsNearbyAlmostDuplicate(arr, 1, -1));
	}
}
