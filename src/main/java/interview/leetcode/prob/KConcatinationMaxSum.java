package interview.leetcode.prob;

/**
 * Given an integer array arr and an integer k, modify the array by repeating it k times.

For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].

Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.

As the answer can be very large, return the answer modulo 109 + 7.

 

Example 1:

Input: arr = [1,2], k = 3
Output: 9
Example 2:

Input: arr = [1,-2,1], k = 5
Output: 2
Example 3:

Input: arr = [-1,-2], k = 7
Output: 0
 

Constraints:

1 <= arr.length <= 105
1 <= k <= 105
-104 <= arr[i] <= 104
Accepted
28,180
Submissions
117,706
 * @author jojo
 * Nov 17, 2022 12:35:00 AM
 */
public class KConcatinationMaxSum {
    public int kConcatenationMaxSum(int[] arr, int k) {
        int mod = (int)(1e9 + 7);
        
        // total length we will min of 2 or provided k for Kandan's algo
        int len = arr.length, tLen = Math.min(2,k) * len;
        
        long maxSum = 0, curSum = 0, totalSum = 0;
        
        // finding the kandan's algo for at max twice the length of the original array 
        for(int i=0; i<tLen; i++){
            int idx = i % len;
            
            curSum += arr[idx];
            
            maxSum = Math.max(maxSum, curSum);
            
            if(curSum < 0){
                curSum = 0;
            }
            
            // taking a total sum
            if(i < len){
                totalSum += arr[i];
            }
        }
        
        // this is based on a mathametical formula where kundan's algo for atmost twice length 
        // 
        while(totalSum > 0 && k-- > 2){
            maxSum = (maxSum + totalSum) % mod;
        }
        
        return (int) (maxSum % mod);
    }
}
