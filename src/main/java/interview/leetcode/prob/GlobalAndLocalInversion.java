package interview.leetcode.prob;

/**
 * We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.

The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].

The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].

Return true if and only if the number of global inversions is equal to the number of local inversions.

Example 1:

Input: A = [1,0,2]
Output: true
Explanation: There is 1 global inversion, and 1 local inversion.
Example 2:

Input: A = [1,2,0]
Output: false
Explanation: There are 2 global inversions, and 1 local inversion.
Note:

A will be a permutation of [0, 1, ..., A.length - 1].
A will have length in range [1, 5000].
The time limit for this problem has been reduced.

 * @author jojo
 *Feb 14, 201811:23:52 AM
 */
public class GlobalAndLocalInversion {
    public boolean isIdealPermutation(int[] A) {
        // local -->i and i+1
        // global --> i+1 to n-1
        // so all we need to make sure is local count is not < global count;
        
        int maxSoFar = Integer.MIN_VALUE;
        for(int i=0; i<A.length - 2; i++){
            maxSoFar = Math.max(maxSoFar, A[i]);
            
            if(maxSoFar > A[i+2]){
                return false;
            }
        }
        
        return true;
    }
}
