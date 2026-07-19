package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer array nums. Each element in nums is 1, 2 or 3. In each operation, you can remove an element from nums. Return the minimum number of operations to make nums non-decreasing.

 

Example 1:

Input: nums = [2,1,3,2,1]

Output: 3

Explanation:

One of the optimal solutions is to remove nums[0], nums[2] and nums[3].

Example 2:

Input: nums = [1,3,2,1,3,3]

Output: 2

Explanation:

One of the optimal solutions is to remove nums[1] and nums[2].

Example 3:

Input: nums = [2,2,2,2,3,3]

Output: 0

Explanation:

nums is already non-decreasing.

 

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 3
 

Follow-up: Can you come up with an algorithm that runs in O(n) time complexity?
 
Seen this question in a real interview before?
1/6
Yes
No
Accepted
27,335/63.3K
Acceptance Rate
43.2%
 * 
 * chiranjeebnandy
 * Jul 18, 2026  2026  11:04:43 PM
 */
public class SortingThreeGroups {
	public int minimumOperations_1(List<Integer> nums) {
        int dp1 = 0, dp2 = 0, dp3 = 0;

        for(int n : nums){
            int xDp1 = dp1 + ( n == 1 ? 0 : 1 );
            int xDp2 = Math.min(dp1, dp2) + ( n == 2 ? 0 : 1 );
            int xDp3 = Math.min(dp1, Math.min(dp2, dp3)) + ( n == 3 ? 0 : 1 );

            dp1 = xDp1;
            dp2 = xDp2;
            dp3 = xDp3;
        }

        return Math.min(dp1, Math.min(dp2, dp3));
    }

    // this is the generic verison of previous sol
    public int minimumOperations(List<Integer> nums) {
        int[] dp = new int[4]; // since 1 to 3 nums are allowed 
        
        for(int n : nums){
            int minSofar = Integer.MAX_VALUE;

            for(int i=1; i<=3; i++){
                minSofar = Math.min(minSofar, dp[i]);
                dp[i] = minSofar + ((n == i) ? 0 : 1);
            }
        }

        int min = dp[1];

        for(int i=1; i<=3; i++){
            min = Math.min(min, dp[i]);
        }

        return min;
    }

    // this is same as longest decreasing seq 
    public int minimumOperations_3(List<Integer> nums) {
        List<Integer> list = new ArrayList<>();

        for(int n : nums){
            int idx = findIdx(list, n);

            while(idx < list.size() && list.get(idx) == n){
                idx++;
            }

            if(idx < list.size()){
                list.set(idx, n);
            }
            else{
                list.add(n);
            }
        }

        return nums.size() - list.size();
    }

    private int findIdx(List<Integer> list, int target){
        int beg = 0, end = list.size();

        while(beg < end){
            int mid = beg + (end - beg) / 2;

            if(list.get(mid) < target){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }

        return beg;
    }
}
