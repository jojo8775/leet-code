package interview.leetcode.prob;

/**
 * We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.

 

Example 1:

Input: n = 1, k = 1
Output: 0
Explanation: row 1: 0
Example 2:

Input: n = 2, k = 1
Output: 0
Explanation: 
row 1: 0
row 2: 01
Example 3:

Input: n = 2, k = 2
Output: 1
Explanation: 
row 1: 0
row 2: 01
 

Constraints:

1 <= n <= 30
1 <= k <= 2n - 1
Accepted
133,950
Submissions
312,897
 * @author jojo
 * Oct. 24, 2023 9:44:52 p.m.
 */
public class KthSymbolInGrammar {

	//think of the problem like this
	/*        0
	   /       \
	  0          1
	/   \      /    \
	0     1    1      0
	/ \     / \   / \   / \
	0  1   1   0  1  0  0  1
	*/
	public int kthGrammar(int n, int k) {
        return dfs(n, k, 0);
    }
    
    private int dfs(int n, int k, int rootVal){
        if(n == 1){
            return rootVal;
        }
        
        int totalNodes = (int) Math.pow(2, n - 1);
        
        // if k is less than half, then we need to drill down on the left side and discard the right side
        if(k <= (totalNodes / 2)){
            // as per problem if the node is 0 then left is 0 and right is 1. left child should be the next root.
            int nextRoot = rootVal == 0 ? 0 : 1;
            
            return dfs(n-1, k, nextRoot);
        }
        // if k is greater than half then we can skip left tree and drill down on right tree only.
        else {
            // as per problem if the node is 0 then left is 0 and right is 1. left child should be the next root.
            int nextRoot = rootVal == 0 ? 1 : 0;
            
            return dfs(n-1, k - (totalNodes / 2), nextRoot);
        }
    }
    
    public int kthGrammar_adv(int n, int k) {
        if(n == 1){
            return 0;
        }
        
        if(k % 2 == 0){
            if(kthGrammar(n-1, k/2) == 0){
                return 1;
            }
            else {
                return 0;
            }
        }
        else{
            if(kthGrammar(n-1, (k+1)/2) == 0){
                return 0;
            }
            else{
                return 1;   
            }            
        }
    }
}
