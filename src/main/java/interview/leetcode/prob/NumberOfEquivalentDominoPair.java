package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.

Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].

 

Example 1:

Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
Output: 1
 

Constraints:

1 <= dominoes.length <= 40000
1 <= dominoes[i][j] <= 9
Accepted
9,659
Submissions
21,538
 * @author jojo
 * Aug 30, 2019 1:25:22 AM
 */
public class NumberOfEquivalentDominoPair {
	public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int count = 0;
        for(int[] d : dominoes){
            int min = Math.min(d[0], d[1]), max = Math.max(d[0], d[1]);
            int key = min * 10 + max;
            
            count += map.getOrDefault(key, 0);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        return count;
    }
}
