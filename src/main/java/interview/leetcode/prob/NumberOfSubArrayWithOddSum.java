package interview.leetcode.prob;

/**
 * Given an array of integers arr, return the number of subarrays with an odd sum.

Since the answer can be very large, return it modulo 109 + 7.

 

Example 1:

Input: arr = [1,3,5]
Output: 4
Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
All sub-arrays sum are [1,4,9,3,8,5].
Odd sums are [1,9,3,5] so the answer is 4.
Example 2:

Input: arr = [2,4,6]
Output: 0
Explanation: All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
All sub-arrays sum are [2,6,12,4,10,6].
All sub-arrays have even sum and the answer is 0.
Example 3:

Input: arr = [1,2,3,4,5,6,7]
Output: 16
 

Constraints:

1 <= arr.length <= 105
1 <= arr[i] <= 100
Accepted
19,539
Submissions
45,210
 * @author jojo
 * Dec 22, 2021 11:32:45 PM
 */
public class NumberOfSubArrayWithOddSum {
    /**
    * If we know the number of even and odd subarrays that end at the previous element, we can figure out how many even and odd subarrays we have for element n:

If n is even, we increase the number of even subarrays; the number of odd subarrays does not change.
If n is odd, the number of odd subarrays is the previous number of even subarrays + 1. The number of even subarrays is the previous number of odd subarrays.
Looking at this example:

Array: [1, 1, 2, 1]  Total
Odd:    1  1  1  3     6
Even:   0  1  2  1
    */
    public int numOfSubarrays(int[] arr) {
        int len = arr.length, mod = 1000000007, sum = 0;
        
        int oddSum = 0, evenSum = 0;
        
        for(int i=0; i<len; i++){
            if(arr[i] % 2 == 1){
                int temp = oddSum;
                oddSum = evenSum + 1;
                evenSum = temp;
            }
            else{
                evenSum++;
            }
            
            sum += oddSum;
            sum %= mod;
        }
        
        return sum;
    }
}
