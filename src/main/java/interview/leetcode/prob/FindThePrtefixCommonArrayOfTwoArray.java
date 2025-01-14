package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given two 0-indexed integer permutations A and B of length n.

A prefix common array of A and B is an array C such that C[i] is equal to the count of numbers that are present at or before the index i in both A and B.

Return the prefix common array of A and B.

A sequence of n integers is called a permutation if it contains all integers from 1 to n exactly once.

 

Example 1:

Input: A = [1,3,2,4], B = [3,1,2,4]
Output: [0,2,3,4]
Explanation: At i = 0: no number is common, so C[0] = 0.
At i = 1: 1 and 3 are common in A and B, so C[1] = 2.
At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
At i = 3: 1, 2, 3, and 4 are common in A and B, so C[3] = 4.
Example 2:

Input: A = [2,3,1], B = [3,1,2]
Output: [0,1,3]
Explanation: At i = 0: no number is common, so C[0] = 0.
At i = 1: only 3 is common in A and B, so C[1] = 1.
At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
 

Constraints:

1 <= A.length == B.length == n <= 50
1 <= A[i], B[i] <= n
It is guaranteed that A and B are both a permutation of n integers.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
86.5K
Submissions
102.8K
Acceptance Rate
84.2%
 * 
 * Jan 13, 2025 - 10:20:32 PM
 * Jojo 
 */
public class FindThePrtefixCommonArrayOfTwoArray {
	public int[] findThePrefixCommonArray(int[] A, int[] B) {
        // since in the problem it is mentioned that all the number are unique so 
        // we need to count the number of freq and if they are 2 then it is present in both 

        int[] freq = new int[A.length + 1]; // since it is 1 based. 
        int[] arr = new int[A.length];

        int count = 0;

        for(int i=0; i<A.length; i++){
            freq[A[i]]++;

            if(freq[A[i]] == 2){
                count++;
            }

            freq[B[i]]++;
            
            if(freq[B[i]] == 2){
                count++;
            }

            arr[i] = count;
        }

        return arr;
    }

    public int[] findThePrefixCommonArray_1(int[] A, int[] B) {
       Map<Integer, Integer> map1 = new HashMap<>();

        int[] arr = new int[A.length];

       for(int i=0; i<A.length; i++){
            map1.put(A[i], map1.getOrDefault(A[i], 0) + 1);

            for(int j=0; j<=i; j++){
                arr[i] += map1.getOrDefault(B[j], 0);    
            }
       }

       return arr;
    }
}
