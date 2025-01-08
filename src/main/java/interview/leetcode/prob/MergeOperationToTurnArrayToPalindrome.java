package interview.leetcode.prob;

/**
 * You are given an array nums consisting of positive integers.

You can perform the following operation on the array any number of times:

Choose any two adjacent elements and replace them with their sum.
For example, if nums = [1,2,3,1], you can apply one operation to make it [1,5,1].
Return the minimum number of operations needed to turn the array into a palindrome.

 

Example 1:

Input: nums = [4,3,2,1,2,3,1]
Output: 2
Explanation: We can turn the array into a palindrome in 2 operations as follows:
- Apply the operation on the fourth and fifth element of the array, nums becomes equal to [4,3,2,3,3,1].
- Apply the operation on the fifth and sixth element of the array, nums becomes equal to [4,3,2,3,4].
The array [4,3,2,3,4] is a palindrome.
It can be shown that 2 is the minimum number of operations needed.
Example 2:

Input: nums = [1,2,3,4]
Output: 3
Explanation: We do the operation 3 times in any position, we obtain the array [10] at the end which is a palindrome.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 106
Seen this question in a real interview before?
1/5
Yes
No
Accepted
15.9K
Submissions
23K
Acceptance Rate
69.1%

 * 
 * Jan 6, 2025 - 2:13:02 AM
 * Jojo 
 */
public class MergeOperationToTurnArrayToPalindrome {
	public int minimumOperations(int[] nums) {
        // twopoint + Greedy
        int left = 0, right = nums.length - 1;
        int leftSum = nums[left], rightSum = nums[right];

        int count = 0;

        while(left < right){
            if(leftSum == rightSum){
                leftSum = nums[left + 1];
                rightSum = nums[right - 1];

                left++;
                right--;
            }
            else if(leftSum > rightSum){
                rightSum += nums[right - 1];
                right--;
                count++;
            }
            else{
                leftSum += nums[left + 1];
                left++;
                count++;
            }
        }

        return count;
    }
}