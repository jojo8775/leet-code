package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given an array of integers arr and an integer d. In one step you can jump from index i to index:

i + x where: i + x < arr.length and 0 < x <= d.
i - x where: i - x >= 0 and 0 < x <= d.
In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).

You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.

Notice that you can not jump outside of the array at any time.

 

Example 1:


Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
Output: 4
Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9. You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
Similarly You cannot jump from index 3 to index 2 or index 1.
Example 2:

Input: arr = [3,3,3,3,3], d = 3
Output: 1
Explanation: You can start at any index. You always cannot jump to any index.
Example 3:

Input: arr = [7,6,5,4,3,2,1], d = 1
Output: 7
Explanation: Start at index 0. You can visit all the indicies. 
 

Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 105
1 <= d <= arr.length
Accepted
22,764
Submissions
36,409
 * @author jojo
 * Nov 27, 2022 3:20:47 PM
 */
public class JumpGameV {
	public int maxJumps(int[] arr, int d) {
        int result = 0;
        
        // to improve runtime. 
        int[] memo = new int[arr.length];
        Arrays.fill(memo, -1);
        
        for(int i=0; i<arr.length; i++){
            int val = dp(arr, i, d, memo);
            result = Math.max(result, val);
        }
        
        // because the result is 1 based 
        return result + 1;
    }
    
    private int dp(int[] arr, int idx, int d, int[] memo){
        if(memo[idx] != -1){
            return memo[idx];
        }
        
        int left = 0, right = 0;
        
        boolean leftJumpAllowed = true, rightJumpAllowed = true;
        
        for(int i=1; i<=d && (leftJumpAllowed || rightJumpAllowed); i++){
            int leftIdx = idx - i, rightIdx = idx + i;
            
            if(leftIdx >= 0 && leftIdx < arr.length && leftJumpAllowed){
                if(arr[leftIdx] < arr[idx]){
                    // relation
                    left = Math.max(left, 1 + dp(arr, leftIdx, d, memo));
                }
                else{
                    // there has been one 'K' building found which is taller than 'i' building
                    // so cannot move left anymore.
                    leftJumpAllowed = false;   
                }
            }
            
            if(rightIdx >= 0 && rightIdx < arr.length && rightJumpAllowed){
                if(arr[rightIdx] < arr[idx]){
                    // relation
                    right = Math.max(right, 1 + dp(arr, rightIdx, d, memo));
                }
                else{
                    // there has been one 'K' building found which is taller than 'i' building
                    // so cannot move right anymore.
                    rightJumpAllowed = false;   
                }
            }
        }
        
        memo[idx] = Math.max(left, right);
        
        return memo[idx];
    }
}
