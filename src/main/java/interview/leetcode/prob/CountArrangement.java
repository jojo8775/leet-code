package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 ≤ i ≤ N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
Now given N, how many beautiful arrangements can you construct?

Example 1:
Input: 2
Output: 2
Explanation: 

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 * @author jojo
 *Mar 25, 201710:16:56 AM
 */
public class CountArrangement {
    public int countArrangement(int N) {
        int count = backTrack(N, new HashSet<Integer>(), 1);
        
        return count;
    }
    
    private int backTrack(int n, Set<Integer> visited, int idx){
        // base case when the end is reached
        if(idx == n + 1){
            return 1;
        }
        
        int count = 0;
        for(int i=1; i<=n; i++){
            // if the division is possible
            if(idx % i == 0 || i % idx == 0){
                // if new entry is not visited 
                if(visited.add(i)){
                    count += backTrack(n, visited, idx + 1);
                    visited.remove(i);
                }
            }
        }
        
        return count;
    }
}
