package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The width of such a ramp is j - i.

Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.

 

Example 1:

Input: nums = [6,0,8,2,1,5]
Output: 4
Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.
Example 2:

Input: nums = [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.
 

Constraints:

2 <= nums.length <= 5 * 104
0 <= nums[i] <= 5 * 104
Accepted
37,565
Submissions
76,711
 * @author jojo
 * Nov 27, 2022 1:56:19 PM
 */
public class MaximumWidthRamp {
	public int maxWidthRamp(int[] nums) {
        int n = nums.length, len = 0;
        
        List<Integer> dscList = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            if(dscList.isEmpty() || nums[dscList.get(dscList.size() - 1)] > nums[i]){
                dscList.add(i);
            }
            else{
                int beg = 0, end = dscList.size();
                
                while(beg < end){
                    int mid = beg + (end - beg)/2;
                    
                    if(nums[dscList.get(mid)] > nums[i]){
                        beg = mid + 1;
                    }
                    else{
                        end = mid;
                    }
                }
                
                len = Math.max(len, i - dscList.get(beg));
            }
        }
        
        return len;
    }
}
