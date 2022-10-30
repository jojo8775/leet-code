package interview.leetcode.prob;

import java.util.Arrays;

/**
 * You are given an integer array nums and an integer k. You may partition nums into one or more subsequences such that each element in nums appears in exactly one of the subsequences.

Return the minimum number of subsequences needed such that the difference between the maximum and minimum values in each subsequence is at most k.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: nums = [3,6,1,2,5], k = 2
Output: 2
Explanation:
We can partition nums into the two subsequences [3,1,2] and [6,5].
The difference between the maximum and minimum value in the first subsequence is 3 - 1 = 2.
The difference between the maximum and minimum value in the second subsequence is 6 - 5 = 1.
Since two subsequences were created, we return 2. It can be shown that 2 is the minimum number of subsequences needed.
Example 2:

Input: nums = [1,2,3], k = 1
Output: 2
Explanation:
We can partition nums into the two subsequences [1,2] and [3].
The difference between the maximum and minimum value in the first subsequence is 2 - 1 = 1.
The difference between the maximum and minimum value in the second subsequence is 3 - 3 = 0.
Since two subsequences were created, we return 2. Note that another optimal solution is to partition nums into the two subsequences [1] and [2,3].
Example 3:

Input: nums = [2,2,4,5], k = 0
Output: 3
Explanation:
We can partition nums into the three subsequences [2,2], [4], and [5].
The difference between the maximum and minimum value in the first subsequences is 2 - 2 = 0.
The difference between the maximum and minimum value in the second subsequences is 4 - 4 = 0.
The difference between the maximum and minimum value in the third subsequences is 5 - 5 = 0.
Since three subsequences were created, we return 3. It can be shown that 3 is the minimum number of subsequences needed.
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 105
0 <= k <= 105
Accepted
25,887
Submissions
35,740
 * @author jojo
 * Oct 27, 2022 8:34:07 PM
 */
public class PartitionArraySuchThatMaximumDifferenceIsK {
    public int partitionArray(int[] nums, int k) {
        // sorting because it is a subsequence
        Arrays.sort(nums);
        
        int min = nums[0], max = nums[0], count = 1;
        
        for(int i=0; i<nums.length; i++){
            
            // taking max and min for each group
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
            
            // if the difference between max and min is more than k in a group a new group is needed
            if(max - min > k){
                count++;
                // reseting the max and min for the new group
                max = min = nums[i];
            }
        }
        
        return count;
    }
}
