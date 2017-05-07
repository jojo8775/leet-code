package interview.leetcode.prob;

import java.util.Map;
import java.util.TreeMap;

/**
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
 * @author jojo
 *
 */
public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        // array to store rolling sum. This is same as the Range sum problem
        long[] sumArr = new long[nums.length + 1];
        
        TreeMap<Long, Integer> sumMap = new TreeMap<>();
        
        // adding the default entry for ease of computation
        sumMap.put(0l, 1);
        
        // indexing all sum so that it is efficient to calculate sum of any sub array of nums
        for(int i=1; i<sumArr.length; i++){
            sumArr[i] = sumArr[i-1] + nums[i-1];
            sumMap.put(sumArr[i], sumMap.getOrDefault(sumArr[i], 0) + 1);
        }
        
        int count = 0;
        
        for(int i=0; i<nums.length; i++){
            
            // for the first entry it will be removial of default entry which was added on line 8
            sumMap.put(sumArr[i], sumMap.get(sumArr[i]) - 1);
            if(sumMap.get(sumArr[i]) == 0){
                sumMap.remove(sumArr[i]);
            }
            
            // i + upper + 1 because end is exclusive in submap
            for(Map.Entry<Long, Integer> entry : sumMap.subMap(sumArr[i] + lower, sumArr[i] + upper + 1).entrySet()){ 
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
