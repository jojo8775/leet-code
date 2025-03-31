package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * A sequence x1, x2, ..., xn is Fibonacci-like if:

n >= 3
xi + xi+1 == xi+2 for all i + 2 <= n
Given a strictly increasing array arr of positive integers forming a sequence, return the length of the longest Fibonacci-like subsequence of arr. If one does not exist, return 0.

A subsequence is derived from another sequence arr by deleting any number of elements (including none) from arr, without changing the order of the remaining elements. For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].

 

Example 1:

Input: arr = [1,2,3,4,5,6,7,8]
Output: 5
Explanation: The longest subsequence that is fibonacci-like: [1,2,3,5,8].
Example 2:

Input: arr = [1,3,7,11,12,14,18]
Output: 3
Explanation: The longest subsequence that is fibonacci-like: [1,11,12], [3,11,14] or [7,11,18].
 

Constraints:

3 <= arr.length <= 1000
1 <= arr[i] < arr[i + 1] <= 109
Seen this question in a real interview before?
1/5
Yes
No
Accepted
79.7K
Submissions
159.1K
Acceptance Rate
50.1%
Topics
 * 
 * Feb 26, 2025 - 7:43:05 PM
 * Jojo 
 */
public class LengthOfLongestFibonacciSubsequence {
	public int lenLongestFibSubseq(int[] arr) {
        Set<Integer> set = new HashSet<>();

        for(int n : arr){
            set.add(n);
        }

        int maxLen = 0;

        for(int i=0; i<arr.length-2; i++){
            for(int j=i+1; j<arr.length-1; j++){
                int prev = arr[i];
                int cur = arr[j];

                int next = prev + cur;

                int curLen = 2;

                while(set.contains(next)){
                    curLen++;
                    prev = cur;
                    cur = next;
                    next = prev + cur;
                }

                if(curLen > 2){
                    maxLen = Math.max(maxLen, curLen);
                }
            }
        }

        return maxLen;
    }
}
