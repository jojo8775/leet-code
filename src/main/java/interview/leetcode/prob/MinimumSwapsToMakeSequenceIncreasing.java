package interview.leetcode.prob;

/**
 * We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation: 
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.
Note:

A, B are arrays with the same length, and that length will be in the range [1, 1000].
A[i], B[i] are integer values in the range [0, 2000].

 * @author jojo
 * Dec 12, 2018 11:47:34 PM
 */
public class MinimumSwapsToMakeSequenceIncreasing {
	public int minSwap(int[] A, int[] B) {
        int len = A.length;
        
        // idea is to maintain valid state only to previous step.
        // withSwap represents minimum swaps by making current swaps
        // noSwap represents minimum swaps by not making current swaps
        int[] withSwap = new int[len], noSwap = new int[len];
        withSwap[0] = 1;
        
        for(int i=1; i<len; i++){
            // represents when i-1 swap has no effect on current swap and both immediate 
        	// left and diagonal left are smaller than current
            if(A[i] > A[i-1] && B[i] > B[i-1] && A[i] > B[i-1] && B[i] > A[i-1]){
                withSwap[i] = Math.min(withSwap[i-1], noSwap[i-1]) + 1;
                noSwap[i] = Math.min(withSwap[i-1], noSwap[i-1]);
            }
            
            // represents condition when i-1 swapped then i can be swapped, 
            // if i-1 didn't swap then i cannot be swapped.
            else if(A[i] > A[i-1] && B[i] > B[i-1]){
                withSwap[i] = withSwap[i-1] + 1;
                noSwap[i] = noSwap[i-1];
            }
            
            // represents if i-1 swapped then i cannot be swapped, 
            // if i-1 is not swapped then i needs to be swapped.
            else{
                withSwap[i] = noSwap[i-1] + 1;
                noSwap[i] = withSwap[i-1];
            }
        }
        
        return Math.min(withSwap[len-1], noSwap[len-1]);
    }
}
