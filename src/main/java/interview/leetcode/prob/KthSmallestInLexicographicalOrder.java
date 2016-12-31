package interview.leetcode.prob;

/**
 * Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.

Note: 1 ≤ k ≤ n ≤ 109.

Example:

Input:
n: 13   k: 2

Output:
10

Explanation:
The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 * @author jojo
 *
 */
public class KthSmallestInLexicographicalOrder {
    public int findKthNumber(int n, int k) {
        // the range are from 1,2,3--9 on level one 
        // level two 10,11,... 19
        
        long cur = 1;
        // since we are making the current as 1 so has to reduce k by 1
        k--;
        
        while(k>0){
            long steps = calculateSteps(n, cur , cur + 1);
            // safe to jump to cur + 1 -- this means we can go to next child node
            if(steps <= k){
                cur++;
                k -= steps;
            }
            // k lies between cur and cur + 1 so need to go one level down
            else{
                cur *= 10;
                k--;
            }
        }
        
        return (int) cur;
    }
    
    private long calculateSteps(int n, long first, long last){
        long steps = 0;
        while(first <= n){
            steps += Math.min(last, n + 1) - first;
            // going to the next level
            first *= 10;
            last *= 10;
        }
        
        return steps;
    }
}
