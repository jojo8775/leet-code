package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.
Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.
 * @author jojo
 *
 */
public class MatchsticksToSquare {
    public boolean makesquare(int[] nums) {
        if (nums.length < 4) {
            return false;
        }

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 4 != 0) {
            return false;
        }
        
        Arrays.sort(nums);
        
        for(int i=0, j=nums.length - 1; i<j; i++, j--){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        return dfs(nums, new int[4], 0, sum / 4);
    }

    private boolean dfs(int[] nums, int[] sides, int idx, int target) {
        if (idx == nums.length) {
            if (sides[0] == sides[1] && sides[1] == sides[2] && sides[2] == sides[3]) {
                return true;
            }

            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (sides[i] + nums[idx] > target) {
                continue;
            }

            sides[i] += nums[idx];
            if (dfs(nums, sides, idx + 1, target)) {
                return true;
            }
            sides[i] -= nums[idx];
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new MatchsticksToSquare().makesquare(new int[] { 1, 1, 2, 2, 2 }));
        System.out.println(new MatchsticksToSquare().makesquare(new int[] { 3, 3, 3, 3, 4 }));
    }
}
