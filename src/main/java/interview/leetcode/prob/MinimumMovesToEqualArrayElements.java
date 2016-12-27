package interview.leetcode.prob;

/**
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.

Example:

Input:
[1,2,3]

Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * @author jojo
 *
 */
public class MinimumMovesToEqualArrayElements {
    public int minMoves(int[] nums) {
        // idea is to think the problem in a different way. 
        // we can consider the problem statement as, there can only be one element which can be reduced at a time
        
        // finding the maximium number 
        int min = Integer.MAX_VALUE;
        for(int n : nums){
            min = Math.min(n, min);
        }
        
        //number of moves required to make all elements same 
        int numberOfMoves = 0;
        for(int n : nums){
            numberOfMoves += (n - min);
        }
        
        return numberOfMoves;
    }
}
