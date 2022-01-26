package interview.leetcode.prob.paid;

/**
 * Suppose you have n integers labeled 1 through n. A permutation of those n integers perm (1-indexed) is considered a beautiful arrangement if for every i (1 <= i <= n), either of the following is true:

perm[i] is divisible by i.
i is divisible by perm[i].
Given an integer n, return the number of the beautiful arrangements that you can construct.

 

Example 1:

Input: n = 2
Output: 2
Explanation: 
The first beautiful arrangement is [1,2]:
    - perm[1] = 1 is divisible by i = 1
    - perm[2] = 2 is divisible by i = 2
The second beautiful arrangement is [2,1]:
    - perm[1] = 2 is divisible by i = 1
    - i = 2 is divisible by perm[2] = 1
Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 15
Accepted
118,440
Submissions
185,490
 * @author jojo
 * Jan 19, 2022 12:28:02 AM
 */
public class BeautifulArrangement {
    int count = 0;
    
    public int countArrangement(int n) {
        backtrack(new boolean[n + 1], 1, n);
        return count;
    }
    
    private void backtrack(boolean[] visited, int pos, int len){
        if(pos > len){
            count++;
            return;
        }
        
        for(int i=1; i<=len; i++){
            if(!visited[i] && (i % pos == 0 || pos % i == 0)){
                visited[i] = true;
                backtrack(visited, pos + 1, len);
                visited[i] = false;
            }
        }
    }
}
