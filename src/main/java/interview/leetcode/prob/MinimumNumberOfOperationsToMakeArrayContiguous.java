package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You are given an integer array nums. In one operation, you can replace any element in nums with any integer.

nums is considered continuous if both of the following conditions are fulfilled:

All elements in nums are unique.
The difference between the maximum element and the minimum element in nums equals nums.length - 1.
For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.

Return the minimum number of operations to make nums continuous.

 

Example 1:

Input: nums = [4,2,5,3]
Output: 0
Explanation: nums is already continuous.
Example 2:

Input: nums = [1,2,3,5,6]
Output: 1
Explanation: One possible solution is to change the last element to 4.
The resulting array is [1,2,3,5,4], which is continuous.
Example 3:

Input: nums = [1,10,100,1000]
Output: 3
Explanation: One possible solution is to:
- Change the second element to 2.
- Change the third element to 3.
- Change the fourth element to 4.
The resulting array is [1,2,3,4], which is continuous.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
Accepted
77,944
Submissions
148,308
 * 
 * Nov 17, 2024 - 1:23:26 AM
 * Jojo 
 */
public class MinimumNumberOfOperationsToMakeArrayContiguous {
	public int minOperations_BS(int[] nums) {
        Set<Integer> uniqueNums = new HashSet<>();
        for(int n : nums){
            uniqueNums.add(n);
        }
        
        List<Integer> list = new ArrayList<>();
        for(int n : uniqueNums){
            list.add(n);
        }
        
        Collections.sort(list);
        
        int originalLen = nums.length;
        
        int minOperation = Integer.MAX_VALUE;
        
        for(int i=0; i<list.size(); i++){
            int assumedStart = list.get(i);
            
            int expectedEnd = assumedStart + originalLen - 1;
            
            int expectedEndIdx = findIdx(list, expectedEnd);
            
            int diff = originalLen - (expectedEndIdx - i);
            
            //System.out.println("s: " + assumedStart + "  e: " + expectedEnd + "   eidx: " + expectedEndIdx + "  d: " + diff);
            
            minOperation = Math.min(minOperation, diff);
            
            if(minOperation == 0){
                break;
            }
        }
        
        return minOperation;
    }
    
    private int findIdx(List<Integer> list, int target){
        int beg = 0, end = list.size();
        
        while(beg < end){
            int mid = beg + (end - beg) / 2;
            
            if(list.get(mid) <= target){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }
        
        return beg;
    }
    
    // this is using sliding window. 
    public int minOperations(int[] nums) {
        Arrays.sort(nums);
        
        List<Integer> list = new ArrayList<>();
        int prev = nums[0];
        
        for(int i=1; i<nums.length; i++){
            if(prev != nums[i]){
                list.add(prev);
                prev = nums[i];
            }
        }
        
        list.add(prev);
        
        int originalLen = nums.length;
        
        int minOperation = Integer.MAX_VALUE;
        
        for(int i=0, j=0; i<list.size(); i++){
            int assumedStart = list.get(i);
            int expectedEnd = assumedStart + originalLen - 1;
            
            while(j < list.size() && list.get(j) <= expectedEnd){
                j++;
            }
            
            int diff = originalLen - (j - i);
            
            minOperation = Math.min(minOperation, diff);
        }
        
        return minOperation;
    }
}
